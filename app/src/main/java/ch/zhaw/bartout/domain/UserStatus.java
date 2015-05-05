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
    private final double alcoholBreakDownInOneHour = 0.15;
    private final int[] alcoholLevelsForEvent = {1,2,3,5,6,7,8,9,10};

    public UserStatus(User user) {
        this.user = user;
        legalAlcoholLimit = 0.49;
        consumptions = new ArrayList<Consumption>();
    }

    /**
     * is a user fit to drive
     * @return true if the user is fit to drive or false if he isn't
     */
    public boolean fitToDrive() {
        if(fitToDriveDuration() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * alcohol level of the user
     * @return alcohol level in per mill
     */
    public double getAlcoholLevel() {
        double alcoholVolume = 0;
        for(Consumption consumption : consumptions){
            double alocholVolume = getAlcoholVolumeOfConsumption(consumption);
            double durationBetweenStartAndEndInSec = getDurationBetweenOneAndNextConsumption(consumption);
            double alcoholBreakDown = getAlcoholBreakDownOfDuration(durationBetweenStartAndEndInSec);
            double alcoholVolumeMinusBreakDown = alocholVolume-alcoholBreakDown;
            if (alcoholVolumeMinusBreakDown<0){
                alcoholVolumeMinusBreakDown=0;
            }
            alcoholVolume+=alcoholVolumeMinusBreakDown;
        }
        return alcoholVolume;
    }

    /**
     * Gets the time until the user is fit to drive
     * @return duration in seconds
     */
    public long fitToDriveDuration() {
        long fitToDriveDuration = (long) Math.round((getAlcoholLevel()-legalAlcoholLimit)/ alcoholBreakDownInOneHour *60*60);
        if(fitToDriveDuration < 0) fitToDriveDuration = 0;
        return fitToDriveDuration;
    }

    public void addConsumption(Consumption consumption){
        if(consumption == null){
            throw  new IllegalArgumentException("consumption is null");
        }
        consumptions.add(consumption);
        refreshEvents();
    }

    public boolean removeConsumption(Consumption consumption){
        if(consumption == null){
            throw  new IllegalArgumentException("consumption is null");
        }else if(!consumptions.contains(consumption)){
            throw  new IllegalArgumentException("consumption not found in list");
        }
        boolean removeValue = consumptions.remove(consumption);
        refreshEvents();
        return removeValue;
    }

    public List<Consumption> getConsumptions(){
        return Collections.unmodifiableList(consumptions);
    }

    private void refreshEvents() {
        deleteEventsFromUser();
        addAllFitToDriveEvents();
        addAllAlcoholLevelEvents();
    }

    private void deleteEventsFromUser() {
        List<ChronicleEvent> events = Chronicle.getActiveChronicle().getChronicleEvents(UserStatusChronicleEvent.class);
        for(ChronicleEvent chronicleEvent: events){
            UserStatusChronicleEvent userStatusChronicleEvent = (UserStatusChronicleEvent) chronicleEvent;
            if(userStatusChronicleEvent.getUser().getName().equals(user.getName())){
                Chronicle.getActiveChronicle().removeEvent(chronicleEvent);
            }
        }
    }

    private void addAllAlcoholLevelEvents() {
        for(int level: alcoholLevelsForEvent){
            for(Calendar calendar: getMomentsOfSpecificAlcoholLevel(level,true)){
                addAlcoholLevelChronicleEvent(calendar,level);
            }
        }
    }

    private void addAllFitToDriveEvents(){
        for(Calendar calendar: getMomentsOfSpecificAlcoholLevel(legalAlcoholLimit,true)){
            addFitToDriveEvent(false,calendar);
        }
        for(Calendar calendar: getMomentsOfSpecificAlcoholLevel(legalAlcoholLimit,false)){
            addFitToDriveEvent(true,calendar);
        }
    }

    private void addUserStatusChronicleEvent(ChronicleEvent chronicleEvent,Calendar calendar){
        chronicleEvent.setMoment(calendar);
        Chronicle.getActiveChronicle().addEvent(chronicleEvent);
    }

    /**
     *
     * @param level alcohol level
     * @param increase if true, it gets only a moment, when the level is increasing, else only if decreasing
     * @return
     */
    private List<Calendar>  getMomentsOfSpecificAlcoholLevel(double level, boolean increase) {
        List<Calendar> calendars = new ArrayList<Calendar>();
        double alcoholVolume = 0;
        boolean eventShouldHappen = true;
        for(Consumption consumption : consumptions){
            double alcoholVolumeBeforeBreakDown = alcoholVolume + getAlcoholVolumeOfConsumption(consumption);
            if(alcoholVolumeBeforeBreakDown>=level && eventShouldHappen){
                if (increase){
                    calendars.add(consumption.getConsumptionTime());
                    eventShouldHappen = false;
                }else{
                    int durationOfBreakDownInSecOfDiff = (int)Math.round(getDurationOfBreakDown(alcoholVolumeBeforeBreakDown-level)*60*60);
                    Calendar consumptionTimeWithDurationOfBreakDown = Calendar.getInstance();
                    consumptionTimeWithDurationOfBreakDown.setTime(consumption.getConsumptionTime().getTime());
                    consumptionTimeWithDurationOfBreakDown.add(Calendar.SECOND,durationOfBreakDownInSecOfDiff);
                    boolean hasNext = (consumptions.indexOf(consumption)<consumptions.size()-1);
                    if(hasNext){
                        Consumption next = consumptions.get(consumptions.indexOf(consumption));
                        if (consumptionTimeWithDurationOfBreakDown.before(next.getConsumptionTime())){
                            calendars.add(consumptionTimeWithDurationOfBreakDown);
                            eventShouldHappen = false;
                        }
                    }else{
                        calendars.add(consumptionTimeWithDurationOfBreakDown);
                        eventShouldHappen = false;
                    }
                }
            }
            double durationBetweenStartAndEndInSec = getDurationBetweenOneAndNextConsumption(consumption);
            double alcoholBreakDown = getAlcoholBreakDownOfDuration(durationBetweenStartAndEndInSec);
            double alcoholVolumeMinusBreakDown = alcoholVolumeBeforeBreakDown-alcoholBreakDown;
            if (alcoholVolumeMinusBreakDown<0){
                alcoholVolumeMinusBreakDown=0;
            }
            if (alcoholVolumeBeforeBreakDown>level && level > alcoholVolumeMinusBreakDown){
                eventShouldHappen = true;
            }
            alcoholVolume=alcoholVolumeMinusBreakDown;
        }
        return calendars;
    }

    private void addFitToDriveEvent(boolean isFitToDrive, Calendar calendar) {
        ChronicleEvent chronicleEvent = new FitToDriveChronicleEvent(user.copy(),isFitToDrive);
        addUserStatusChronicleEvent(chronicleEvent,calendar);
    }

    private void addAlcoholLevelChronicleEvent(Calendar calendar, int level) {
        ChronicleEvent chronicleEvent = new AlcoholLevelChronicleEvent(user.copy(),level);
        addUserStatusChronicleEvent(chronicleEvent, calendar);
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
        if (user.getWeight() == 0){
            throw new IllegalArgumentException("the user weight must not be 0");
        }
        double reducedWeightOfUser = reductionFactor*user.getWeight();
        double bloodAlcoholContent = pureAlcoholInG/reducedWeightOfUser;
        double alocholVolume = bloodAlcoholContent-bloodAlcoholContent*resorptionDeficit;
        return alocholVolume;
    }

    /**
     *
     * @param consumption
     * @return duration in seconds
     */
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

    /**
     *
     * @param level
     * @return duration in hours
     */
    private double getDurationOfBreakDown(double level){
        return level/ alcoholBreakDownInOneHour;
    }

    private double getAlcoholBreakDownOfDuration(double duration){
        return duration/60/60* this.alcoholBreakDownInOneHour;
    }

}
