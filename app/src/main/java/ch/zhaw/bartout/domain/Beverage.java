package ch.zhaw.bartout.domain;

/**
 * Created by tomekman on 04.04.15.
 */
public class Beverage {
    private String name;
    private double volume;
    private double alcoholicStrength;

    public Beverage (String name, double volume, double alcoholicStrength) {
        this.name = name;
        this.volume = volume;
        this.alcoholicStrength = alcoholicStrength;
    }

    public double getAlcoholicStrength() {
        return alcoholicStrength;
    }

    public double getVolume () {
        return volume;
    }
}
