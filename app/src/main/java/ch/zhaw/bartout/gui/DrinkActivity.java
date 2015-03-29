package ch.zhaw.bartout.gui;


import ch.zhaw.bartout.R;

public class DrinkActivity extends BaseActivity {

    public DrinkActivity() {
        super(R.layout.activity_drink);
    }

    @Override
    int getNameRes() {
        return R.string.title_drink;
    }
}
