package ch.zhaw.bartout.gui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;

public class DrinkActivity extends BaseActivity {

    //Beverage
    private String beverageName;
    private Float beverageVolume;
    private Float beverageAlcoholic;

    //GUI
    private EditText editTextBeverageName;
    private EditText editTextBeverageVolume;
    private EditText editTextAlcoholicStrength;
    private ListView listView;

    private Bartour bartour;

    public DrinkActivity() {
        super(R.layout.activity_drink);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bartour = Bartout.getInstance().getActiveBartour();

        editTextBeverageName = new EditText(this);
        editTextBeverageVolume = new EditText(this);
        editTextAlcoholicStrength = new EditText(this);

        listView = (ListView) findViewById(R.id.list_users);


    }

    @Override
    protected int getNameRes() {
        return R.string.title_drink;
    }

    public void buttonBierLargeOnClick(View view) {

        bartour = Bartout.getInstance().getActiveBartour();

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.5");
        editTextAlcoholicStrength.setText("5.0");
    }

    public void buttonBierSmallOnClick(View view) {

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier Stange");
        editTextBeverageVolume.setText("0.3");
        editTextAlcoholicStrength.setText("5.0");

    }

    public void buttonWineOnClick(View view) {

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Glas Wein");
        editTextBeverageVolume.setText("0.1");
        editTextAlcoholicStrength.setText("14.0");

    }

    public void buttonCoctailStrongOnClick(View view) {

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Coctail stark");
        editTextBeverageVolume.setText("0.1");
        editTextAlcoholicStrength.setText("12.0");

    }

    public void buttonCoctailSoftOnClick(View view) {

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.2");
        editTextAlcoholicStrength.setText("5.0");

    }

    public void buttonShotOnClick(View view) {

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.5");
        editTextAlcoholicStrength.setText("5.0");

    }

    public void buttonOtherBeverageOnClick(View view) {

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("<Getränkname eintragen>");
        editTextBeverageVolume.setText("");
        editTextAlcoholicStrength.setText("");

    }

    public void buttonDrinkOnClick(View view) {

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.EdithTextBeverageAlcoholicStrength);

        editTextBeverageName.setText("Bier gross");
        editTextBeverageVolume.setText("0.5");
        editTextAlcoholicStrength.setText("5.0");

    }
}
