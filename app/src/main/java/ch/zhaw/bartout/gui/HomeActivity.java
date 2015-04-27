package ch.zhaw.bartout.gui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;



public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Bartout bartout;
    private Toast toast;
    private ActionMode selectionMode;
    private BartoursAdapter bartoursAdapter;

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
        bartoursAdapter = new BartoursAdapter(
                this,
                bartout.getBartours()
        );
        listView.setAdapter(bartoursAdapter);

        listView.setOnItemClickListener(this);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if(selectionMode != null) return false;

                listView.setItemChecked(position, true);
                listView.setOnItemClickListener(null);
                selectionMode = startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        selectionMode = null;
                        listView.setItemChecked(position, false);
                        listView.setOnItemClickListener(HomeActivity.this);
                    }
                });
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newBartourButton);
        fab.attachToListView(listView);

        if(bartout.getActiveBartour() != null){
            fab.setColorNormal(Color.GRAY);
        }else{
            fab.setColorNormal(Color.parseColor("#ff0f9d58"));
        }
    }

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
}
