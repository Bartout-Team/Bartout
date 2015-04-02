package ch.zhaw.bartout.controller;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
    private RadioButton manRadioButton;
    private RadioButton womannRadioButton;
    private EditText weightEditText;
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
        // if the user is null, its a new User
        if (user==null){
            user = new User();
        }
        getDialog().setTitle(getString(R.string.title_user));
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        initializeGuiVariables(view);
        initializeGuiElements();
        initializeListeners();

        return view;
    }

    private void initializeListeners() {
        usernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                user.setName(s.toString());
            }
        });
        geschlechtRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
            }
        });
    }

    private void initializeGuiVariables(View view) {
        usernameEdit = (EditText) view.findViewById(R.id.usernameEdit);
        geschlechtRadioGroup = (RadioGroup)view.findViewById(R.id.geschlechtRadioGroup);
        manRadioButton = (RadioButton)view.findViewById(R.id.user_man);
        womannRadioButton = (RadioButton)view.findViewById(R.id.user_woman);
        weightEditText = (EditText)view.findViewById(R.id.weightEditText);
        okButton = (Button)view.findViewById(R.id.userOkButton);
        abbrechenButton = (Button)view.findViewById(R.id.userOkButton);
    }

    private void initializeGuiElements() {
        usernameEdit.setText(user.getName());
        if (user.isMan()){
            geschlechtRadioGroup.check(R.id.user_man);
        }else{
            geschlechtRadioGroup.check(R.id.user_woman);
        }
        weightEditText.setText(Integer.toString(user.getWeight()));
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
        mListener.onClose(user);
    }

    public void userCancelButtonOnClick(View view){
        mListener.onClose(null);
    }



}
