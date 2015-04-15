package ch.zhaw.bartout.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.User;

/**
 * Created by bwa on 03.04.2015.
 */
public class DriveFitnessAdapter extends ArrayAdapter<User> {
    private DateFormat dateFormat = SimpleDateFormat.getDateInstance();

    public DriveFitnessAdapter(Context context, List<User> users){
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        User user = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drive_fitness_user_item, parent, false);
        }
        TextView fitToDrive = (TextView) convertView.findViewById(R.id.fit_to_drive);
        TextView name = (TextView) convertView.findViewById(R.id.user_name);
        TextView alcoholLevel = (TextView) convertView.findViewById(R.id.user_alcohol_level);
        TextView textFitToDriveDuration = (TextView) convertView.findViewById(R.id.fit_to_drive_duration);
        ImageView imagefitToDrive = (ImageView) convertView.findViewById(R.id.fit_to_drive_image);
        if(user.getStatus().fitToDrive()) {
            fitToDrive.setText(R.string.ok);
            imagefitToDrive.setVisibility(View.INVISIBLE);
        } else {
            fitToDrive.setText(R.string.no);
        }
        name.setText(user.getName());
        alcoholLevel.setText(user.getStatus().getAlcoholLevel()+ "‰");
        long fitToDriveDuration = user.getStatus().fitToDriveDuration();
        if(fitToDriveDuration != 0) {
            textFitToDriveDuration.setText("in " + fitToDriveDuration / 60 / 60 + "h");
        } else {
            textFitToDriveDuration.setText("");
        }
        return convertView;
    }
}
