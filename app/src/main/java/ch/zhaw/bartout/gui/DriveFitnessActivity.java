package ch.zhaw.bartout.gui;

import android.os.Bundle;
import android.widget.ListView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;

public class DriveFitnessActivity extends BaseActivity {
    private Bartour bartour;
    private ListView listView;
    private DriveFitnessAdapter adapter;

    public DriveFitnessActivity(){
        super(R.layout.activity_drive_fitness);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bartour = Bartout.getInstance().getActiveBartour();

        listView = (ListView) findViewById(R.id.list_drive_fitness);
        adapter = new DriveFitnessAdapter(
                this,
                bartour.getUsers()
        );
        listView.setAdapter(adapter);
    }

    @Override
    protected int getNameRes() {
        return R.string.title_drive_fitness;
    }

    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}