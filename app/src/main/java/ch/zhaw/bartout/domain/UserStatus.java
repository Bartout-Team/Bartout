package ch.zhaw.bartout.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by bwa on 29.03.2015.
 */
public class UserStatus implements Serializable {
    private User user;
    private double legalAlcoholLimit;
    private List<Consumption> consumptions;
    private final double alcoholWeight = 0.81;
    private final double reductionFactorWomen = 0.6;
    private final double reductionFactorMen = 0.7;
    private final double resorptionDeficit = 0.2;
    private final double alcoholBreakDown = 0.15;

    public UserStatus(User user) {
        this.user = user;
        legalAlcoholLimit = 0.5;
        consumptions = new ArrayList<Consumption>();
    }

    public boolean fitToDrive() {
        if(fitToDriveDuration() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public double getAlcoholLevel() {
        Calendar now = Calendar.getInstance();
        double alcoholVolume = 0;
        for(Consumption consumption : consumptions){
            double pureAlcoholInMl = consumption.getVolume() * consumption.getAlcoholicStrength();
            double pureAlcoholInG = pureAlcoholInMl*alcoholWeight;
            double reductionFactor;
            if(user.isMan()){
                reductionFactor = reductionFactorMen;
            }else{
                reductionFactor = reductionFactorWomen;
            }
            double reducedWeightOfUser = reductionFactor*user.getWeight();
            double bloodAlcoholContent = pureAlcoholInG/reducedWeightOfUser;
            double alocholVolume = bloodAlcoholContent-bloodAlcoholContent*resorptionDeficit;
            Calendar startTime = consumption.getConsumptionTime();
            Calendar endTime;
            int indexOfNextConsumption = consumptions.indexOf(consumption)+1;
            if (indexOfNextConsumption==consumptions.size()){
                endTime = now;
            }else{
                endTime = consumptions.get(indexOfNextConsumption).getConsumptionTime();
            }
            double durationBetweenStartAndEndInSec = (endTime.getTimeInMillis()-startTime.getTimeInMillis())/1000;
            double alcoholAbbau = durationBetweenStartAndEndInSec/60/60* alcoholBreakDown;
            double alcoholVolumeMinusAbbau = alocholVolume-alcoholAbbau;
            if (alcoholVolumeMinusAbbau<0){
                alcoholVolumeMinusAbbau=0;
            }
            alcoholVolume+=alcoholVolumeMinusAbbau;
        }
        return alcoholVolume;
    }

    /**
     *
     * @return duration in seconds
     */
    public long fitToDriveDuration() {
        long fitToDriveDuration = (long) ((getAlcoholLevel()-legalAlcoholLimit)/alcoholBreakDown*60*60);
        if(fitToDriveDuration < 0) fitToDriveDuration = 0;
        return fitToDriveDuration;
    }

    public void addConsumption(Consumption consumption){
        consumptions.add(consumption);
    }

    public boolean removeConsumption(Consumption consumption){
        return consumptions.remove(consumption);
    }

    public List<Consumption> getConsumptions(){
        return Collections.unmodifiableList(consumptions);
    }
}