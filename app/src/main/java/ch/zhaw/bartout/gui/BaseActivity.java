package ch.zhaw.bartout.gui;

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

import java.util.Arrays;
import java.util.Map;

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
        getActionBar().setHomeButtonEnabled(true);
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
        mDrawerList.setItemChecked(Arrays.asList(mMenuItems).indexOf(getString(getNameRes())), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        int menuItem = getMenuResId(position);
        Intent intent = null;
        switch(menuItem){
            case R.string.title_home:
                intent = new Intent(this, HomeActivity.class);
                break;
            case R.string.title_search:
                intent = new Intent(this, SearchActivity.class);
                break;
            default:
                throw new IllegalStateException("Handle all Menues from Drawer!");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
                return R.string.title_edit;
            case 3:
                return R.string.title_drink;
            case 4:
                return R.string.title_ranking;
            case 5:
                return R.string.title_drive;
        }
        throw new IllegalArgumentException("position");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
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

    abstract int getNameRes();
}
