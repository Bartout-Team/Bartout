package ch.zhaw.bartout.controller;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartout;
import ch.zhaw.bartout.model.User;
import ch.zhaw.bartout.model.Bartour;

public class BartourActivity extends BaseActivity implements UserFragment.OnFragmentInteractionListener {
    private Bartour bartour;
    private ListView listView;
    private boolean isNew;

    private static final String USER_FRAGMENT_LABEL = "fragment_user";

    // GUI Elements
    private EditText editTextName;

    public static final int BARTOUR_ACTIVITY_REQUEST_CODE = 1;

    public BartourActivity() {
        super(R.layout.activity_bartour);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editTextName = (EditText) findViewById(R.id.tour_name);

        bartour = Bartout.getInstance().getActiveBartour();
        if(bartour == null){
            isNew = true;
            bartour = new Bartour();
        }
        listView = (ListView) findViewById(R.id.list_users);
        UsersAdapter adapter = new UsersAdapter(
                this,
                bartour.getUsers()
        );
        listView.setAdapter(adapter);
        editTextName.setText(bartour.getName());
    }

    @Override
    protected int getNameRes() {
        return R.string.title_bartour;
    }

    public void addUserButtonOnClick(View view){
        showUserFragment(null);
    }

    private void showUserFragment(User user){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(USER_FRAGMENT_LABEL);
        if(prev != null){
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        UserFragment newFragment = UserFragment.newInstance(user);
        newFragment.show(ft, USER_FRAGMENT_LABEL);
    }

    @Override
    public void onClose(User user) {
        getFragmentManager().popBackStackImmediate();
        if(user != null) {
            if(!bartour.getUsers().contains(user)){
                bartour.addUser(user);
                ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
            }
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void saveBartourButtonOnClick(View view) {
        if(editTextName.getText().toString().trim().isEmpty()){
            editTextName.setError(getString(R.string.error_empty));
            return;
        }
        if(bartour.getUsers().isEmpty()){
            ((TextView)findViewById(R.id.textView)).setError(getString(R.string.error_no_users));
            return;
        }
        bartour.setName(editTextName.getText().toString());
        if(isNew){
            Bartout.getInstance().addBartour(bartour);
        }
        finishActivity(BARTOUR_ACTIVITY_REQUEST_CODE);
        finish();
    }

    public void deleteUser(User user){
        bartour.getUsers().remove(user);
        ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
    }

    public void editUser(User user){
        showUserFragment(user);
    }

}
