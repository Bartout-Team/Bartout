package ch.zhaw.bartout.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartout;

/**
 * Created by srueg on 24.03.15.
 */
public abstract class BaseActivity extends Activity implements ListView.OnItemClickListener{

    private final int mLayoutId;
    private final boolean mHomeAsUp;

    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ArrayList<String> menuItems = new ArrayList<>();

    public BaseActivity(int layoutId){
        this(layoutId, true);
    }

    public BaseActivity(int layoutId, boolean homeAsUp){
        mLayoutId = layoutId;
        mHomeAsUp = homeAsUp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mLayoutId);
        getActionBar().setDisplayHomeAsUpEnabled(mHomeAsUp);
        //getActionBar().setHomeButtonEnabled(true);
        setTitle(getString(getNameRes()));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                getActionBar().setTitle(getString(getNameRes()));
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                getActionBar().setTitle(R.string.app_name);
            }
        };
        drawerToggle.syncState();
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, android.R.id.text1, menuItems));
        drawerList.setOnItemClickListener(this);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public void onResume(){
        super.onResume();
        menuItems.clear();
        menuItems.add(getString(R.string.title_home));
        menuItems.add(getString(R.string.title_search));
        // Other menu items are only displayed when a bartour is active.
        if(Bartout.getInstance().getActiveBartour() != null){
            menuItems.add(getString(R.string.title_bartour));
            menuItems.add(getString(R.string.title_drink));
            menuItems.add(getString(R.string.title_ranking));
            menuItems.add(getString(R.string.title_drive_fitness));
        }
        drawerList.setItemChecked(menuItems.indexOf(getString(getNameRes())), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        String menuItem = ((TextView)view).getText().toString();
        Intent intent;

        if(menuItem.equals(getString(R.string.title_home))){
            intent = new Intent(this, HomeActivity.class);
        }else if(menuItem.equals(getString(R.string.title_search))){
            intent = new Intent(this, SearchActivity.class);
        }else if(menuItem.equals(getString(R.string.title_bartour))){
            intent = new Intent(this, BartourActivity.class);
        }else if(menuItem.equals(getString(R.string.title_drink))){
            intent = new Intent(this, DrinkActivity.class);
        }else if(menuItem.equals(getString(R.string.title_ranking))){
            intent = new Intent(this, RankingActivity.class);
        }else if(menuItem.equals(getString(R.string.title_drive_fitness))){
            intent = new Intent(this, DriveFitnessActivity.class);
        }else {
            throw new IllegalStateException("Handle all Menues from Drawer!");
        }

        /*switch(menuItem){
            case R.string.title_home:
                intent = new Intent(this, HomeActivity.class);
                break;
            case R.string.title_search:
                intent = new Intent(this, SearchActivity.class);
                break;
            case  R.string.title_bartour:
                intent = new Intent(this, BartourActivity.class);
                break;
            case R.string.title_drink:
                intent = new Intent(this, DrinkActivity.class);
                break;
            case R.string.title_ranking:
                intent = new Intent(this, RankingActivity.class);
                break;
            case R.string.title_drive_fitness:
                intent = new Intent(this, DriveFitnessActivity.class);
                break;
            default:
                throw new IllegalStateException("Handle all Menues from Drawer!");
        }*/
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        drawerLayout.closeDrawer(drawerList);
    }

    /*
    private int getMenuResId(int position){
        switch (position){
            case 0:
                return R.string.title_home;
            case 1:
                return R.string.title_search;
            case 2:
                return R.string.title_bartour;
            case 3:
                return R.string.title_drink;
            case 4:
                return R.string.title_ranking;
            case 5:
                return R.string.title_drive_fitness;
        }
        throw new IllegalArgumentException("position");
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(drawerList)) {
                    drawerLayout.closeDrawer(drawerList);
                } else {
                    drawerLayout.openDrawer(drawerList);
                }
                break;
            case R.id.action_settings:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    abstract protected int getNameRes();
}
