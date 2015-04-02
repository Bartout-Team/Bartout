package ch.zhaw.bartout.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartour;
import ch.zhaw.bartout.model.Bartout;



public class HomeActivity extends BaseActivity {

    private ListView listView;
    private Bartout bartout;
    private Toast toast;

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
        bartout = Bartout.getInstance();
    }

    public void newBartourButtonOnClick(View view){
        if(bartout.getActiveBartour() == null){
            Intent intent = new Intent(this, BartourActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityForResult(intent, BartourActivity.BARTOUR_ACTIVITY_REQUEST_CODE);
        }else{
            if(toast == null){
                toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_bartout_acive), Toast.LENGTH_LONG);
            }
            if(toast.getView().getWindowVisibility() != View.VISIBLE){
                toast.show();
            }
        }
    }

    public void onResume() {
        super.onResume();

        listView = (ListView) findViewById(R.id.list_view);
        BartoursAdapter adapter = new BartoursAdapter(
                this,
                bartout.getBartours()
        );
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newBartourButton);
        fab.attachToListView(listView);

        if(bartout.getActiveBartour() != null){
            fab.setColorNormal(Color.GRAY);
        }
    }
}