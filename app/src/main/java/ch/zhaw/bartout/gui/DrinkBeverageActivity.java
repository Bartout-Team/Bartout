package ch.zhaw.bartout.gui;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;
import ch.zhaw.bartout.domain.User;
import ch.zhaw.bartout.domain.Consumption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

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
    private List<User> users = new ArrayList<>();

    //Keys
    private String beverageNameKey = "beverageNameKey";
    private String beverageVolumeKey = "beverageVolumeKey";
    private String beverageAlcoholicKey = "beverageAlcoholicKey";

    public DrinkBeverageActivity() {super(R.layout.activity_drink_beverage);}

    @Override
    protected int getNameRes() {
        return R.string.title_drink;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.content.Intent intent = getIntent();

        beverageName = intent.getStringExtra(beverageNameKey);
        beverageVolume = intent.getDoubleExtra(beverageVolumeKey, 0.0);
        beverageAlcoholicStrength = intent.getDoubleExtra(beverageAlcoholicKey, 0.0);

        bartour = Bartout.getInstance().getActiveBartour();
        users = bartour.getUsers();

        editTextBeverageName = (EditText) findViewById(R.id.editTextBeverageName);
        editTextBeverageVolume = (EditText) findViewById(R.id.editTextBeverageVolume);
        editTextAlcoholicStrength = (EditText) findViewById(R.id.editTextBeverageAlcoholicStrength);

        editTextBeverageName.setText(beverageName);
        editTextBeverageVolume.setText(Double.toString(beverageVolume));
        editTextAlcoholicStrength.setText(Double.toString(beverageAlcoholicStrength));

        listViewUsers = (ListView) findViewById(R.id.listViewDrinkUsers);

        UserBeverageAdapter userBeverageAdapter = new UserBeverageAdapter(this, users);
        listViewUsers.setAdapter(userBeverageAdapter);

    }

    public void buttonDrinkOnClick(View view) {

        beverageName = editTextBeverageName.getText().toString();
        beverageVolume = Double.parseDouble(editTextBeverageVolume.getText().toString());
        beverageAlcoholicStrength = Double.parseDouble(editTextAlcoholicStrength.getText().toString());


        for (int x= 0; x<listViewUsers.getAdapter().getCount();x++ ){

            View v = getViewByPosition(x, listViewUsers);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.beverageUserItemCheckBox);

            if (checkBox.isChecked()) {
                Consumption consumption = new Consumption(beverageName, beverageAlcoholicStrength, beverageVolume*10);

                User u = users.get(x);
                u.getStatus().addConsumption(consumption);
            }
        }
        Intent intent = new Intent(this, DrinkActivity.class);
        startActivity(intent);
    }

    private View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

}
