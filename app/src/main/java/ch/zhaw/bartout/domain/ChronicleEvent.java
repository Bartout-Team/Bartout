package ch.zhaw.bartout.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class ChronicleEvent implements Serializable {

    private Date moment;

    public ChronicleEvent() {

    }

    public Date getMoment() {
        return moment;
    }
}
