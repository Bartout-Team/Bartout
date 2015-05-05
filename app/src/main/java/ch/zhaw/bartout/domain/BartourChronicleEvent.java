package ch.zhaw.bartout.domain;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;

import ch.zhaw.bartout.R;

/**
 * Created by srueg on 05.05.15.
 */
public class BartourChronicleEvent extends ChronicleEvent {

    private boolean isStart = true;
    private String name;

    public BartourChronicleEvent(String name, boolean isStart){
        this.isStart = isStart;
        this.name = name;
    }
    public BartourChronicleEvent(String name, boolean isStart,Calendar startTime){
        this(name,isStart);
        setMoment(startTime);
    }

    @Override
    public String getDisplayName() {
        return "Bartour <b>" + name + "</b> hat " + (isStart ? "Gestartet" : "Geendet") + ".";
    }

    @Override
    public View getView(Context context){
        View view = super.getView(context);

        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_start_flag));

        return view;
    }
}
