package ch.zhaw.bartout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwa on 29.03.2015.
 */
public class UserStatus implements Serializable {
    private double legalAlcoholLimit;
    private List<Consumption> consumptions;

    public UserStatus() {
        legalAlcoholLimit = 0.5;
    }

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
        return (long) ((getAlcoholLevel()-legalAlcoholLimit)/0.15*60*60);
    }
}
