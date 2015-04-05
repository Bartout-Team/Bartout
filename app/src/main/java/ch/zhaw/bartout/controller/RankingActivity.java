package ch.zhaw.bartout.controller;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Ranking;

public class RankingActivity extends BaseActivity {

    public static String EXTRA_RANKING = "ch.zhaw.bartout.controller.Ranking";
    private Ranking ranking;
    private ListView listView;

    public RankingActivity(){
        super(R.layout.activity_ranking);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.content.Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ranking = (Ranking) bundle.getSerializable(EXTRA_RANKING);

        listView = (ListView) findViewById(R.id.list_view);
        RankingAdapter adapter = new RankingAdapter(
                this,
                ranking.getRanking()
        );
        listView.setAdapter(adapter);

    }

    @Override
    protected int getNameRes() {
        return R.string.title_ranking;
    }
}
