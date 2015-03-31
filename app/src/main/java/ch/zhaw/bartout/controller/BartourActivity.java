package ch.zhaw.bartout.controller;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.view.View;

import ch.zhaw.bartout.R;

public class BartourActivity extends BaseActivity implements UserFragment.OnFragmentInteractionListener {

    public BartourActivity() {
        super(R.layout.activity_bartour);
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
        UserFragment newFragment = UserFragment.newInstance();
        newFragment.show(ft, "fragment_user");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
