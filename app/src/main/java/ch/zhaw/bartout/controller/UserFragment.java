package ch.zhaw.bartout.controller;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartour;
import ch.zhaw.bartout.model.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends DialogFragment {

    private static final String ARG_USER = "user";

    private OnFragmentInteractionListener mListener;
    private User user;

    //Oberflächenelemente
    private EditText usernameEdit;
    private RadioGroup geschlechtRadioGroup;
    private Button okButton;
    private Button abbrechenButton;

    public static UserFragment newInstance(User user) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        user = (User) getArguments().getSerializable(ARG_USER);
        getDialog().setTitle(getString(R.string.title_user));
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        usernameEdit = (EditText) view.findViewById(R.id.usernameEdit);
        geschlechtRadioGroup = (RadioGroup)view.findViewById(R.id.geschlechtRadioGroup);
        okButton = (Button)view.findViewById(R.id.userOkButton);
        abbrechenButton = (Button)view.findViewById(R.id.userOkButton);
        geschlechtRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onClose(User user);
    }

    public void userOkButtonOnClick(View view){

    }

    public void userCancelButtonOnClick(View view){

    }
}
