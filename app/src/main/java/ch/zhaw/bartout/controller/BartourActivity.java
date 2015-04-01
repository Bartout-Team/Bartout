package ch.zhaw.bartout.controller;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.User;
import ch.zhaw.bartout.model.Bartour;

public class BartourActivity extends BaseActivity implements UserFragment.OnFragmentInteractionListener {
    private Bartour bartour;
    private ListView listView;

    public BartourActivity() {
        super(R.layout.activity_bartour);
        bartour = new Bartour();
    }

    @Override
    protected int getNameRes() {
        return R.string.title_bartour;
    }

    public void addUserButtonOnClick(View view){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("fragment_user");
        if(prev != null){
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        UserFragment newFragment = UserFragment.newInstance(null);
        newFragment.show(ft, "fragment_user");
    }

    @Override
    public void onClose(User user) {
        if(user != null) {
            bartour.addUser(user);
        }

    }

    public void onResume() {
        super.onResume();
        listView = (ListView) findViewById(R.id.list_users);
        UsersAdapter adapter = new UsersAdapter(
                this,
                bartour.getUsers()
        );
        listView.setAdapter(adapter);
    }

    public void saveBartourButtonOnClick(View view) {
        EditText editText = (EditText) findViewById(R.id.tour_name);
        bartour.setName(editText.getText().toString());

    }

}
