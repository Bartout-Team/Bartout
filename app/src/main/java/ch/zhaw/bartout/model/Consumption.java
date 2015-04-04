package ch.zhaw.bartout.model;

/**
 * Created by tomekman on 04.04.15.
 */
public class Consumption {

    private double alcoholicStrength;
    private double volume;
    private Beverage beverage;

    public Consumption(Beverage beverage) {
        this.beverage = beverage;
        alcoholicStrength = beverage.getAlcoholicStrength();
        volume = beverage.getVolume();
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
}
