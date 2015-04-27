package ch.zhaw.bartout.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class ChronicleEvent implements Serializable {

    private Calendar moment;
    private String displayName;

    public ChronicleEvent() {
        moment = Calendar.getInstance();
    }

    public Calendar getMoment() {
        return moment;
    }
    public String getDisplayName(){
        return displayName;
    };
}
