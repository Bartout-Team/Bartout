package ch.zhaw.bartout.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.RankingUser;
import ch.zhaw.bartout.model.User;

/**
 * Created by serge on 05.04.2015.
 */
public class RankingAdapter  extends ArrayAdapter<RankingUser> {

    public RankingAdapter(Context context, List<RankingUser> users){
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final RankingUser user = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ranking_user_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name_text);
        name.setText(String.format(Integer.toString(position+1) + ". " +  user.getUser().getName()));

        TextView alcoholLevel = (TextView) convertView.findViewById(R.id.user_alcohol_level);
        alcoholLevel.setText(Double.toString(user.getUser().getStatus().getAlcoholLevel())+"‰");
        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.user_alcohol_level_progressBar);
        progressBar.setProgress((int)Math.round(user.getAlcoholLevelInPercent()*100));

        return convertView;
    }

}
