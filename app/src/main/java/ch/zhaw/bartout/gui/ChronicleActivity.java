package ch.zhaw.bartout.gui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;

public class ChronicleActivity extends BaseActivity {

    private Bartour bartour;

    public static final String CHRONICLE_EXTRA_BARTOUR = "chronicle_extra_bartour";

    public ChronicleActivity(){
        super(R.layout.activity_chronicle);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.content.Intent intent = getIntent();
        Bundle b = intent.getExtras();
        bartour = (Bartour) b.getSerializable(CHRONICLE_EXTRA_BARTOUR);


        ListView listView = (ListView) findViewById(R.id.list_view);

        ChronicleAdapter bartoursAdapter = new ChronicleAdapter(
                this,
                bartour.getChronicle().getChronicleEvents()
        );
        listView.setAdapter(bartoursAdapter);
    }

    @Override
    protected int getNameRes() {
        return R.string.title_chronicle;
    }
}
