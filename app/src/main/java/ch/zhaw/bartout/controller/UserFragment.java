package ch.zhaw.bartout.controller;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends DialogFragment implements View.OnClickListener {

    private static final String ARG_USER = "user";

    private OnFragmentInteractionListener listener;
    private User user;

    // GUI Elements
    private EditText usernameEdit;
    private EditText userWeightEdit;

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
        userWeightEdit = (EditText) view.findViewById(R.id.userWeightEdit);
        RadioGroup geschlechtRadioGroup = (RadioGroup)view.findViewById(R.id.geschlechtRadioGroup);
        Button okButton = (Button)view.findViewById(R.id.userOkButton);
        Button abbrechenButton = (Button)view.findViewById(R.id.userCancelButton);

        if(user == null){
            user = new User();
        }else{
            usernameEdit.setText(user.getName());
            userWeightEdit.setText(user.getWeight());
        }

        okButton.setOnClickListener(this);
        abbrechenButton.setOnClickListener(this);

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
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userCancelButton:
                listener.onClose(null);
                break;
            case R.id.userOkButton:
                user.setName(usernameEdit.getText().toString());
                user.setWeight(Integer.parseInt(userWeightEdit.getText().toString()));
                listener.onClose(user);
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        public void onClose(User user);
    }
}
