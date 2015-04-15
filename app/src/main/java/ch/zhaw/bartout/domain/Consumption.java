package ch.zhaw.bartout.domain;

import java.util.Calendar;

/**
 * Created by tomekman on 04.04.15.
 */
public class Consumption {

    private double alcoholicStrength;
    private double volume;
    private Beverage beverage;
    private Calendar consumptionTime;

    public Consumption(Beverage beverage) {
        this.beverage = beverage;
        alcoholicStrength = beverage.getAlcoholicStrength();
        volume = beverage.getVolume();
        consumptionTime = Calendar.getInstance();
    }

    public Beverage getBeverage() {
        return beverage;
    }

    /**
     * @return returns volume in ml
     */
    public double getVolume () {
        return volume;
    }

    /**
     * @return returns alcoholic strength in percent (ex. 5% = 0.05)
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

}
