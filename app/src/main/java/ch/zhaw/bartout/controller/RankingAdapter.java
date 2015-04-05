package ch.zhaw.bartout.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.User;

/**
 * Created by serge on 05.04.2015.
 */
public class RankingAdapter  extends ArrayAdapter<User> {

    public RankingAdapter(Context context, ArrayList<User> users){
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final User user = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ranking_user_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name_text);
        name.setText(String.format(Integer.toString(position+1) + ". " +  user.getName()));

        TextView alcoholLevel = (TextView) convertView.findViewById(R.id.user_alcohol_level);
        alcoholLevel.setText(Double.toString(user.getStatus().getAlcoholLevel()));

        return convertView;
    }

}
