package ch.zhaw.bartout.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.bartour.ranking.RankingUser;

/**
 * Created by bwa on 03.04.2015.
 */
public class DriveFitnessAdapter extends ArrayAdapter<RankingUser> {
    private DateFormat dateFormat = SimpleDateFormat.getDateInstance();

    public DriveFitnessAdapter(Context context, List<RankingUser> rankingUsers){
        super(context, 0, rankingUsers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RankingUser rankingUser = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drive_fitness_user_item, parent, false);
        }
        TextView fitToDrive = (TextView) convertView.findViewById(R.id.fit_to_drive);
        TextView name = (TextView) convertView.findViewById(R.id.user_name);
        TextView alcoholLevel = (TextView) convertView.findViewById(R.id.user_alcohol_level);
        TextView textFitToDriveDuration = (TextView) convertView.findViewById(R.id.fit_to_drive_duration);
        ImageView imagefitToDrive = (ImageView) convertView.findViewById(R.id.fit_to_drive_image);
        if(rankingUser.getUser().getStatus().fitToDrive()) {
            fitToDrive.setText(R.string.ok);
            imagefitToDrive.setVisibility(View.INVISIBLE);
        } else {
            fitToDrive.setText(R.string.no);
            imagefitToDrive.setVisibility(View.VISIBLE);
        }
        name.setText(rankingUser.getUser().getName());
        DecimalFormat df = new DecimalFormat("#.##");
        alcoholLevel.setText(df.format(rankingUser.getUser().getStatus().getAlcoholLevel())+ "‰");
        long fitToDriveDuration = rankingUser.getUser().getStatus().fitToDriveDuration();
        if(fitToDriveDuration != 0) {
            textFitToDriveDuration.setText("in " + fitToDriveDuration / 60 / 60 + "h");
        } else {
            textFitToDriveDuration.setText("");
        }
        return convertView;
    }
}
