package ch.zhaw.bartout.gui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ch.zhaw.bartout.R;

public class DriveFitnessActivity extends BaseActivity {

    public DriveFitnessActivity(){
        super(R.layout.activity_drive_fitness);
    }

    @Override
    int getNameRes() {
        return R.string.title_drive_fitness;
    }
}
