package ch.zhaw.bartout.controller;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartout;
import ch.zhaw.bartout.model.Ranking;

public class RankingActivity extends BaseActivity {

    private Ranking ranking;
    private ListView listView;
    private RankingAdapter adapter;

    public RankingActivity(){
        super(R.layout.activity_ranking);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ranking = Bartout.getInstance().getActiveBartour().getRanking();
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new RankingAdapter(
                this,
                ranking.getRanking()
        );
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getNameRes() {
        return R.string.title_ranking;
    }
}
