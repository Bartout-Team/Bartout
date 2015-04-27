package ch.zhaw.bartout.gui;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;

public class DrinkActivity extends BaseActivity {

    //Beverage
    private String beverageName;
    private Double beverageVolume;
    private Double beverageAlcoholic;



    private Bartour bartour;

    public DrinkActivity() {
        super(R.layout.activity_drink);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bartour = Bartout.getInstance().getActiveBartour();

        beverageName = "neues Getränk";
        beverageVolume = 0.0;
        beverageAlcoholic = 0.0;
     }

    @Override
    protected int getNameRes() {
        return R.string.title_drink;
    }

    public void buttonBierLargeOnClick(View view) {

        bartour = Bartout.getInstance().getActiveBartour();

        beverageName = "Bier gross";
        beverageVolume = 0.5;
        beverageAlcoholic = 5.0;


    }

    public void buttonBierSmallOnClick(View view) {

        beverageName = "Bier Stange";
        beverageVolume = 0.3;
        beverageAlcoholic = 5.0;

    }

    public void buttonWineOnClick(View view) {

        beverageName = "Glas Wein";
        beverageVolume = 0.1;
        beverageAlcoholic = 14.0;

    }

    public void buttonCoctailStrongOnClick(View view) {

        beverageName = "Coctail stark";
        beverageVolume = 0.1;
        beverageAlcoholic = 12.0;

    }

    public void buttonCoctailSoftOnClick(View view) {

        beverageName = "Coctail schwach";
        beverageVolume = 0.2;
        beverageAlcoholic = 5.0;

    }

    public void buttonShotOnClick(View view) {

        beverageName = "Shot";
        beverageVolume = 0.2;
        beverageAlcoholic = 40.0;

    }

    public void buttonOtherBeverageOnClick(View view) {

        beverageName = "neues Getränk";
        beverageVolume = 0.0;
        beverageAlcoholic = 0.0;

    }


}
