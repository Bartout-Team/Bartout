package ch.zhaw.bartout.gui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
