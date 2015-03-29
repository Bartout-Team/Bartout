package ch.zhaw.bartout.gui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ch.zhaw.bartout.R;

public class ChronicleActivity extends BaseActivity {

    public ChronicleActivity(){
        super(R.layout.activity_chronicle);
    }

    @Override
    int getNameRes() {
        return R.string.title_chronicle;
    }
}
