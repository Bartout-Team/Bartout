package ch.zhaw.bartout.controller;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
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
    private Activity attachedActivity;

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
        okButton.setOnClickListener(this);
        abbrechenButton.setOnClickListener(this);
    }

    private void initializeGuiVariables(View view) {
        usernameEdit = (EditText) view.findViewById(R.id.usernameEdit);
        geschlechtRadioGroup = (RadioGroup)view.findViewById(R.id.geschlechtRadioGroup);
        manRadioButton = (RadioButton)view.findViewById(R.id.user_man);
        weightEditText = (EditText)view.findViewById(R.id.weightEditText);
        okButton = (Button)view.findViewById(R.id.userOkButton);
        abbrechenButton = (Button)view.findViewById(R.id.userCancelButton);
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
        attachedActivity = activity;
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
                if(isValidInput()){
                    user.setName(usernameEdit.getText().toString());
                    user.setWeight(Integer.parseInt(weightEditText.getText().toString()));
                    user.setMan(manRadioButton.isChecked());
                    listener.onClose(user);
                }else{
                    return;
                }
                break;
        }
        InputMethodManager imm = (InputMethodManager) attachedActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(attachedActivity.getCurrentFocus().getWindowToken(), 0);
    }

    private boolean isValidInput() {
        boolean validInput = true;
        if (usernameEdit.length()==0){
            usernameEdit.setError(getString(R.string.error_empty));
            usernameEdit.requestFocus();
            validInput = false;
        }
        if (weightEditText.length()==0 || weightEditText.getText().toString().equals("0")){
            weightEditText.requestFocus();
            weightEditText.requestFocus();
            weightEditText.setError(getString(R.string.error_zero));
            validInput = false;
        }
        return validInput;
    }


    public interface OnFragmentInteractionListener {
        public void onClose(User user);
    }

}
