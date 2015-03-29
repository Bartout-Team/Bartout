package ch.zhaw.bartout.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartour;
import ch.zhaw.bartout.model.Bartout;


public class HomeActivity extends BaseActivity {

    private ListView listView;

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

        listView = (ListView) findViewById(R.id.list_view);

        BartoursAdapter adapter = new BartoursAdapter(
                this,
                Bartout.getInstance().getBartours()
        );
        listView.setAdapter(adapter);
    }

}