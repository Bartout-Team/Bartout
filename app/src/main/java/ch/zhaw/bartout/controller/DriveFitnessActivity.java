package ch.zhaw.bartout.controller;

import android.os.Bundle;
import android.widget.ListView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartour;
import ch.zhaw.bartout.model.Bartout;

public class DriveFitnessActivity extends BaseActivity {
    private Bartour bartour;
    private ListView listView;

    public DriveFitnessActivity(){
        super(R.layout.activity_drive_fitness);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bartour = Bartout.getInstance().getActiveBartour();

        listView = (ListView) findViewById(R.id.list_drive_fitness);
        DriveFitnessAdapter adapter = new DriveFitnessAdapter(
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
    }

}
