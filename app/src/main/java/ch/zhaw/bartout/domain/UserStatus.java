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
        double alcoholVolume = 0;
        for(Consumption consumption : consumptions){
            double alocholVolume = getAlcoholVolumeOfConsumption(consumption);
            double durationBetweenStartAndEndInSec = getDurationBetweenOneAndNextConsumption(consumption);
            double alcoholBreakDown = durationBetweenStartAndEndInSec/60/60* this.alcoholBreakDown;
            double alcoholVolumeMinusBreakDown = alocholVolume-alcoholBreakDown;
            if (alcoholVolumeMinusBreakDown<0){
                alcoholVolumeMinusBreakDown=0;
            }
            alcoholVolume+=alcoholVolumeMinusBreakDown;
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
        //refreshEvents();
    }

    public boolean removeConsumption(Consumption consumption){
        return consumptions.remove(consumption);
    }

    public List<Consumption> getConsumptions(){
        return Collections.unmodifiableList(consumptions);
    }

    private void refreshEvents() {
        //GetEventsFromUser
        //DeleteEventsFromUser
        //AddNewEventsFromUser

        addIntegerVolumeValueEvent();
        addFitToDriveEvent(true);
    }

    private void addFitToDriveEvent(boolean isFitToDrive) {
        ChronicleEvent chronicleEvent = new FitToDriveChronicleEvent(user.copy(),isFitToDrive);
        Chronicle.getActiveChronicle().addEvent(chronicleEvent);
    }

    private void addIntegerVolumeValueEvent() {
        ChronicleEvent chronicleEvent = new AlcoholLevelChronicleEvent(user.copy());
        Chronicle.getActiveChronicle().addEvent(chronicleEvent);
    }

    /**
     * Calculates the alcohol volume for one consumption (without alcohol breakdown)
     * @param consumption the Consumption, for which the alcohol volume should be calculated
     * @return alcohol volume in ‰
     */
    private double getAlcoholVolumeOfConsumption(Consumption consumption){
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
        return alocholVolume;
    }

    private double getDurationBetweenOneAndNextConsumption(Consumption consumption){
        Calendar startTime = consumption.getConsumptionTime();
        Calendar endTime;
        int indexOfNextConsumption = consumptions.indexOf(consumption)+1;
        if (indexOfNextConsumption==consumptions.size()){
            endTime = Calendar.getInstance();
        }else{
            endTime = consumptions.get(indexOfNextConsumption).getConsumptionTime();
        }
        return (endTime.getTimeInMillis()-startTime.getTimeInMillis())/1000;
    }

}
