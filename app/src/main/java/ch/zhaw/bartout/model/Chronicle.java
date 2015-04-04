package ch.zhaw.bartout.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bwa on 29.03.2015.
 */
public class Chronicle implements Serializable {
    private ArrayList<Event> events;

    public Chronicle() {

    }

    public ArrayList<Event> getEvents() {
        return events;
    }

}
