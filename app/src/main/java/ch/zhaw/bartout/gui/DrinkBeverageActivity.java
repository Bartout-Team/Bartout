package ch.zhaw.bartout.gui;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;
import ch.zhaw.bartout.domain.User;
import ch.zhaw.bartout.domain.Consumption;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DrinkBeverageActivity extends BaseActivity {

    private String beverageName;
    private Double beverageVolume;
    private Double beverageAlcoholicStrength;

    //GUI
    private EditText editTextBeverageName;
    private EditText editTextBeverageVolume;
    private EditText editTextAlcoholicStrength;
    private ListView listViewUsers;

    private Bartour bartour;
    private List<User> users = new ArrayList<User>();

    public DrinkBeverageActivity() {super(R.layout.activity_drink_beverage);}

    @Override
    protected int getNameRes() {
        return R.string.title_drink;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bartour = Bartout.getInstance().getActiveBartour();
        users = bartour.getUsers();

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.editTextBeverageAlcoholicStrength);

        editTextBeverageName.setText(beverageName);
        editTextBeverageVolume.setText(Double.toString(beverageVolume));
        editTextAlcoholicStrength.setText(Double.toString(beverageAlcoholicStrength));

        listViewUsers = (ListView) findViewById(R.id.listViewDrinkUsers);


    }

    public void buttonDrinkOnClick(View view) {

        beverageName = editTextBeverageName.getText().toString();
        beverageVolume = Double.parseDouble(editTextBeverageVolume.getText().toString());
        beverageAlcoholicStrength = Double.parseDouble(editTextAlcoholicStrength.getText().toString());


        Consumption consumption = new Consumption(beverageName, beverageAlcoholicStrength, beverageVolume);


    }

    public void setBeverageName(String beverageName) {
        this.beverageName = beverageName;
    }

    public void setBeverageVolume(Double beverageVolume) {
        this.beverageVolume = beverageVolume;
    }

    public void setBeverageAlcoholicStrength(Double beverageAlcoholicStrength) {
        this.beverageAlcoholicStrength = beverageAlcoholicStrength;
    }
}
