package ch.zhaw.bartout.domain.bartour.user;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by tomekman on 04.04.15.
 */
public class Consumption implements Serializable {

    private String name;
    private double alcoholicStrength;
    private double volume;
    private Calendar consumptionTime;

    public Consumption(){}

    /**
     * Creates a new Consumption
     * @param name name of consumption
     * @param alcoholicStrength alcoholic strength of consumption in percent (decimal: 5% = 5)
     * @param volume volume of consumption in dl
     */
    public Consumption(String name, double alcoholicStrength, double volume) {
        this.name = name;
        this.alcoholicStrength = alcoholicStrength;
        this.volume = volume;
        this.consumptionTime = Calendar.getInstance();
    }

    /**
     * @return returns volume in dl
     */
    public double getVolume () {
        return volume;
    }

    /**
     * @return returns alcoholic strength in percent (ex. 5% = 5)
     */
    public double getAlcoholicStrength() {
        return alcoholicStrength;
    }

    /**
     * @return returns the date and time when the beverage was drunken
     */
    public Calendar getConsumptionTime(){
        return consumptionTime;
    }

    /**
     *
     * @return returns the name of the consumption
     */
    public String getName(){
        return name;
    }

}
