package ch.zhaw.bartout.gui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ch.zhaw.bartout.R;


public class HomeActivity extends BaseActivity {

    public HomeActivity() {
        super(R.layout.activity_home, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getNameRes() {
        return R.string.title_home;
    }

}