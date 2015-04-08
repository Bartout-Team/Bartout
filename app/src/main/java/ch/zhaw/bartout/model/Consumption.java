package ch.zhaw.bartout.model;

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

    public double getVolume () {
        return volume;
    }

    public double getAlcoholicStrength() {
        return alcoholicStrength;
    }

    public Calendar getConsumptionTime(){
        return consumptionTime;
    }
}
