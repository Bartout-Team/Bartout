package ch.zhaw.bartout.gui;


import android.content.Intent;
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

    //Keys
    private String beverageNameKey = "beverageNameKey";
    private String beverageVolumeKey = "beverageVolumeKey";
    private String beverageAlcoholicKey = "beverageAlcoholicKey";



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

        beverageName = "Bier gross";
        beverageVolume = 0.5;
        beverageAlcoholic = 5.0;

        toDrinkBeverageActivity();

    }

    public void buttonBierSmallOnClick(View view) {

        beverageName = "Bier Stange";
        beverageVolume = 0.3;
        beverageAlcoholic = 5.0;

        toDrinkBeverageActivity();

    }

    public void buttonWineOnClick(View view) {

        beverageName = "Glas Wein";
        beverageVolume = 0.1;
        beverageAlcoholic = 14.0;

        toDrinkBeverageActivity();

    }

    public void buttonCoctailStrongOnClick(View view) {

        beverageName = "Coctail stark";
        beverageVolume = 0.1;
        beverageAlcoholic = 12.0;

        toDrinkBeverageActivity();

    }

    public void buttonCoctailSoftOnClick(View view) {

        beverageName = "Coctail schwach";
        beverageVolume = 0.2;
        beverageAlcoholic = 5.0;

        toDrinkBeverageActivity();

    }

    public void buttonShotOnClick(View view) {

        beverageName = "Shot";
        beverageVolume = 0.2;
        beverageAlcoholic = 40.0;

        toDrinkBeverageActivity();

    }

    public void buttonOtherBeverageOnClick(View view) {

        beverageName = "neues Getränk";
        beverageVolume = 0.0;
        beverageAlcoholic = 0.0;

        toDrinkBeverageActivity();

    }

    private void toDrinkBeverageActivity(){

        Intent intent = new Intent(this, DrinkBeverageActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(DrinkBeverageActivity.DRINK_BEVERAGE_CONTENT, bartour);
        intent.putExtras(b);
        b.putString(beverageNameKey, beverageName);
        b.putDouble(beverageVolumeKey, beverageVolume);
        b.putDouble(beverageAlcoholicKey, beverageAlcoholic);
        startActivity(intent);
    }
}
