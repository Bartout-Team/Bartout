package ch.zhaw.bartout.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ch.zhaw.bartout.R;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class ChronicleEvent implements Comparable<ChronicleEvent>, Serializable {

    private Calendar moment;
    private String momentFormated;

    public ChronicleEvent() {
        setMoment(Calendar.getInstance());
    }

    public Calendar getMoment() {
        return moment;
    }

    public void setMoment(Calendar moment){
        this.moment = moment;
        this.momentFormated = new java.text.SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(getMoment().getTime());
    }


    public abstract String getDisplayName();

    public View getView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.chronicle_event_item, null, false);
        TextView t = (TextView) view.findViewById(R.id.text_name);
        t.setText(getDisplayName());

        TextView time = (TextView) view.findViewById(R.id.textView_time);
        time.setText(new SimpleDateFormat("HH:mm").format(moment.getTime()));
        return view;
    }

    @Override
    public int compareTo(ChronicleEvent another) {
        Long instanceTime = getMoment().getTimeInMillis();
        Long anotherTime = another.getMoment().getTimeInMillis();
        if (instanceTime<anotherTime){
            return -1;
        } else if(instanceTime>anotherTime){
            return 1;
        }else{
            return 0;
        }
    }

}
