package ch.zhaw.bartout.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

import ch.zhaw.bartout.R;

/**
 * Created by srueg on 24.03.15.
 */
public abstract class BaseActivity extends Activity implements ListView.OnItemClickListener{

    private final int mLayoutId;
    private final boolean mHomeAsUp;

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mMenuItems;

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

        mMenuItems = new String[getResources().getInteger(R.integer.drawer_item_count)];
        for(int i=0; i<mMenuItems.length; i++){
            mMenuItems[i] = getString(getMenuResId(i));
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close){
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
        mDrawerToggle.syncState();
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, android.R.id.text1, mMenuItems));
        mDrawerList.setOnItemClickListener(this);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onResume(){
        super.onResume();
        mDrawerList.setItemChecked(Arrays.asList(mMenuItems).indexOf(getString(getNameRes())), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        int menuItem = getMenuResId(position);
        Intent intent;
        switch(menuItem){
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
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                break;
            case R.id.action_settings:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    abstract protected int getNameRes();
}
