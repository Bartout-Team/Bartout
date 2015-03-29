package ch.zhaw.bartout.controller;

import android.os.Bundle;

import java.util.ArrayList;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartour;
import ch.zhaw.bartout.model.Bartout;


public class HomeActivity extends BaseActivity {

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    protected int getNameRes() {
        return R.string.title_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}