package ch.zhaw.bartout.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.User;

/**
 * Created by bwa on 01.04.2015.
 */
public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context, ArrayList<User> bartours){
        super(context, 0, bartours);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        User user = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bartour_user_item, parent, false);
        }
        TextView textName = (TextView) convertView.findViewById(R.id.text_name);
        textName.setText(user.getName());
        return convertView;
    }
}
