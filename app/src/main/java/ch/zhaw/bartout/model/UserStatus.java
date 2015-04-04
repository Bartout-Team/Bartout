package ch.zhaw.bartout.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bwa on 29.03.2015.
 */
public class UserStatus implements Serializable {

    private ArrayList<Consumption> consumptions;

    public boolean fitToDrive() {
        return false;
    }

    public double getAlcoholLevel() {
        return 4.5;
    }

    /**
     *
     * @return duration in seconds
     */
    public long fitToDriveDuration() {
        return 45000;
    }
}
