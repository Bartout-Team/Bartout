package ch.zhaw.bartout.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.model.Bartour;


/**
 * Created by srueg on 29.03.15.
 */
public class BartoursAdapter extends ArrayAdapter<Bartour> {

    private DateFormat dateFormat = SimpleDateFormat.getDateInstance();

    public BartoursAdapter(Context context, ArrayList<Bartour> bartours){
        super(context, 0, bartours);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Bartour bartour = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_bartour_item, parent, false);
        }
        TextView textName = (TextView) convertView.findViewById(R.id.text_name);
        TextView textDate = (TextView) convertView.findViewById(R.id.text_date);
        TextView textDuration = (TextView) convertView.findViewById(R.id.text_duration);
        textName.setText(bartour.getName());
        textDate.setText(dateFormat.format(bartour.getStart().getTime()));
        long duration = bartour.getDuration();
        if(duration != -1) {
            textDuration.setText(duration / 60 / 60 + "h ");
        }
        return convertView;
    }
}
