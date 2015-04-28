package ch.zhaw.bartout.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import ch.zhaw.bartout.R;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class ChronicleEvent implements Serializable {

    private Calendar moment;

    public ChronicleEvent() {
        moment = Calendar.getInstance();
    }

    public Calendar getMoment() {
        return moment;
    }

    public abstract String getDisplayName();

    public View getView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.chronicle_event_item, null, false);
        TextView t = (TextView) view.findViewById(R.id.text_name);
        t.setText(getDisplayName());
        return view;
    }
}
