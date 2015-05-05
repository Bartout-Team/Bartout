package ch.zhaw.bartout.gui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;

public class ChronicleActivity extends BaseActivity {

    private Bartour bartour;
    private ChronicleAdapter chronicleAdapter;

    public static final String CHRONICLE_EXTRA_BARTOUR = "chronicle_extra_bartour";

    public ChronicleActivity(){
        super(R.layout.activity_chronicle);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume(){
        super.onResume();
        android.content.Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b != null && b.containsKey(CHRONICLE_EXTRA_BARTOUR)) {
            bartour = (Bartour) b.getSerializable(CHRONICLE_EXTRA_BARTOUR);
        } else {
            bartour = Bartout.getInstance().getActiveBartour();
        }
        ListView listView = (ListView) findViewById(R.id.list_view);

        chronicleAdapter = new ChronicleAdapter(
                this,
                bartour.getChronicle().getChronicleEvents()
        );
        listView.setAdapter(chronicleAdapter);
    }

    @Override
    protected int getNameRes() {
        return R.string.title_chronicle;
    }
}
