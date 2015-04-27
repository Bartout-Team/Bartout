package ch.zhaw.bartout.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.User;

/**
 * Created by bwa on 01.04.2015.
 */
public class UserBeverageAdapter extends ArrayAdapter<User> {

    public UserBeverageAdapter(Context context, List<User> bartours){
        super(context, 0, bartours);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final User user = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bartour_user_item, parent, false);
        }
        TextView textName = (TextView) convertView.findViewById(R.id.text_name);
        textName.setText(user.getName());

        CheckBox userBeverageCheck = (CheckBox) convertView.findViewById(R.id.beverageUserItemCheckBox);



        return convertView;
    }
}
