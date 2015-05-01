package ch.zhaw.bartout.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


/**
 * Created by srueg on 29.03.15.
 */
public class Bartout implements Serializable {

    // Singleton
    private static Bartout _instance;

    /**
     * Gets the singleton instance of Bartout
     * @return singleton instance
     */
    public static Bartout getInstance(){
        if(_instance == null){
            _instance = new Bartout();
        }
        return _instance;
    }
    // end Singleton

    private List<Bartour> bartours;
    private Bartour activeBartour;

    protected Bartout(){
        bartours = new ArrayList<Bartour>();
    }

    public void initializeSampleData(){
        // Sample Daten dürfen nicht in Konstruktor geladen werden,
        // da aufgrund unseres Singleton sonst ein rekursive Endlosschleife aufgerufen wird.
        Calendar end = Calendar.getInstance();
        end.add(Calendar.HOUR, 2);
        addBartour(new Bartour().setName("First Tour").setEnd(end));
        addBartour(new Bartour().setName("Second Tour").setEnd(end));
        addBartour(new Bartour().setName("Third Tour").setEnd(end));
        //activeBartour = null;
        addBartour(new Bartour().setName("Active Tour"));

        for(Bartour tour : bartours){
            activeBartour = tour; //active tour have to be set, because the events would be added to the active chronicle
            User user = new User().setName("First User");
            user.setWeight(70);
            tour.addUser(user);
            user = new User().setName("Second User");
            tour.addUser(user);
            user.setWeight(70);
            user.getStatus().addConsumption(new Consumption("Guinnes",8,5));
            user.getStatus().addConsumption(new Consumption("Stella",5,5));
            user.getStatus().addConsumption(new Consumption("Staropramen",5,5));
            EstablishmentLocationChronicleEvent e = new EstablishmentLocationChronicleEvent();
            e.setType("Bar");
            e.setLatitude(100);
            e.setLongitude(100);
            e.setLocationName("Kennedy's");
            tour.getChronicle().addEvent(e);

            ATMLocationChronicleEvent a = new ATMLocationChronicleEvent();
            a.setLocationName("ZKB");
            a.setLongitude(101);
            a.setLatitude(101);
            tour.getChronicle().addEvent(a);

            user = new User().setName("Third User");
            user.setWeight(70);
            AlcoholLevelChronicleEvent b = new AlcoholLevelChronicleEvent(user,2);
            UserStatus status = new UserStatus(user);
            status.addConsumption(new Consumption("Guinnes", 4.5,  5));
            b.setStatus(status);
        }
    }

    public List<Bartour> getBartours(){
        return Collections.unmodifiableList(bartours);
    }

    public Bartour getActiveBartour(){
        return activeBartour;
    }

    public void addBartour(Bartour bartour) {
        bartours.add(bartour);
        activeBartour = bartour;
        activeBartour.setOnFinishedListener(new Bartour.OnFinishedListener() {
            @Override
            public void OnFinished() {
                activeBartour = null;
            }
        });
    }

    public boolean removeBartour(Bartour bartour){
        return bartours.remove(bartour);
    }

}
