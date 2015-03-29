package ch.zhaw.bartout.controller;

import ch.zhaw.bartout.R;

public class RankingActivity extends BaseActivity {

    public RankingActivity(){
        super(R.layout.activity_ranking);
    }

    @Override
    protected int getNameRes() {
        return R.string.title_ranking;
    }
}
