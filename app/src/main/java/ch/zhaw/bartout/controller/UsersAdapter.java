package ch.zhaw.bartout.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.User;

/**
 * Created by bwa on 01.04.2015.
 */
public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context context, List<User> bartours){
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

        ImageButton buttonEdit = (ImageButton) convertView.findViewById(R.id.imageButtonEdit);
        ImageButton buttonDelete = (ImageButton) convertView.findViewById(R.id.imageButtonDelete);
        buttonEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((BartourActivity)getContext()).editUser(user);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BartourActivity)getContext()).deleteUser(user);
            }
        });

        return convertView;
    }
}
