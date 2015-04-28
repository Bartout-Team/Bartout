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
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.ChronicleEvent;
import ch.zhaw.bartout.domain.RankingChronicleEvent;


/**
 * Created by srueg on 29.03.15.
 */
public class ChronicleAdapter extends ArrayAdapter<ChronicleEvent> {

    private DateFormat dateFormat = SimpleDateFormat.getDateInstance();

    public ChronicleAdapter(Context context, List<ChronicleEvent> chronicleEvents){
        super(context, 0, chronicleEvents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ChronicleEvent event = getItem(position);

        if(convertView == null){
            convertView = event.getView(getContext());
        }

        return convertView;
    }
}
