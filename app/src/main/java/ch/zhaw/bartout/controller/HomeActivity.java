package ch.zhaw.bartout.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

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
            startActivity(intent);
        }else{
            if(toast == null){
                toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_bartout_active), Toast.LENGTH_LONG);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bartour bartour = (Bartour) listView.getItemAtPosition(position);
                if (bartour.getIsActive()) {
                    startActivity(getString(R.string.title_search));
                } else {
                    Intent intent = new Intent(HomeActivity.this, ChronicleActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable(ChronicleActivity.CHRONICLE_EXTRA_BARTOUR, bartour);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newBartourButton);
        fab.attachToListView(listView);

        if(bartout.getActiveBartour() != null){
            fab.setColorNormal(Color.GRAY);
        }
    }
}