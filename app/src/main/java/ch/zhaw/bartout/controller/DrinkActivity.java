package ch.zhaw.bartout.controller;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ch.zhaw.bartout.R;

public class DrinkActivity extends BaseActivity {

    String beverageName;
    Float beverageVolume;
    Float beverageAlcoholic;
    EditText editTextBeverageName = new EditText(this);
    EditText editTextBeverageVolume = new EditText(this);
    EditText editTextAlcoholicStrength = new EditText(this);


    public DrinkActivity() {
        super(R.layout.activity_drink);
    }

    public void buttonBierLargeOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.5");
        editTextAlcoholicStrength.setText("5.0");
    }
    public void buttonBierSmallOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier Stange");
        editTextBeverageVolume.setText("0.3");
        editTextAlcoholicStrength.setText("5.0");

    }
    public void buttonWineOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Glas Wein");
        editTextBeverageVolume.setText("0.1");
        editTextAlcoholicStrength.setText("14.0");

    }
    public void buttonCoctailStrongOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Coctail stark");
        editTextBeverageVolume.setText("0.1");
        editTextAlcoholicStrength.setText("12.0");

    }
    public void buttonCoctailSoftOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.2");
        editTextAlcoholicStrength.setText("5.0");

    }
    public void buttonShotOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.5");
        editTextAlcoholicStrength.setText("5.0");

    }
    public void buttonOtherBeverageOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("<Getränkname eintragen>");
        editTextBeverageVolume.setText("");
        editTextAlcoholicStrength.setText("");

    }

    public void buttonDrinkOnClick(View view){

        editTextBeverageName = (EditText)findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText)findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText)findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.5");
        editTextAlcoholicStrength.setText("5.0");

    }
    @Override
    protected int getNameRes() {
        return R.string.title_drink;
    }
}
