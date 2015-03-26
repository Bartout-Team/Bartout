package ch.zhaw.bartout.gui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

import ch.zhaw.bartout.R;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, HomeFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        onNavigationDrawerItemSelected(R.string.title_home);
    }

    private HashMap<Integer, Fragment.SavedState> states = new HashMap<>();

    @Override
    public void onNavigationDrawerItemSelected(int menuResId) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;
        switch (menuResId) {
            case R.string.title_home:
                fragment = new HomeFragment(); //HomeFragment.getInstance();
                break;
            case R.string.title_search:
                fragment = new SearchFragment(); // SearchFragment.getInstance();
                break;
        }

        if(fragment != null) {
            if(states.containsKey(menuResId)){
                fragment.setInitialSavedState(states.get(menuResId));
            }
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container_fragment, fragment)
                    .addToBackStack(getString(menuResId))
                    .commit();

            //states.put(menuResId, fragmentManager.saveFragmentInstanceState(fragment));

            /* for(Fragment fr : mFragments){
                transaction.hide(fr);
            }
            if(!fragment.isAdded()){
                transaction.add(R.id.container_fragment, fragment, getString(menuResId))
                        .addToBackStack(getString(menuResId))
                        .commit();
                mFragments.add(fragment);
            }else {
                transaction.show(fragment)
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack(getString(menuResId))
                        .commit();
            } */
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
