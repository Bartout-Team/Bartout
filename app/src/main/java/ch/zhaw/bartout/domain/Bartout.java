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
    private static boolean startup = true;

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
        if(startup) {
            startup = false;
            // Sample Daten dürfen nicht in Konstruktor geladen werden,
            // da aufgrund unseres Singleton sonst ein rekursive Endlosschleife aufgerufen wird.
            Calendar end = Calendar.getInstance();
            end.add(Calendar.HOUR, 2);

            for (int i=0 ; i<3 ; i++) {
                Bartour tour = new Bartour();
                tour.setName("Tour #"+i);
                addBartour(tour);

                User user = new User();
                user.setName("First User");
                user.setWeight(70);
                tour.addUser(user);

                user = new User();
                user.setName("Second User");
                user.setWeight(70);
                tour.addUser(user);

                user.getStatus().addConsumption(new Consumption("Guinnes", 8, 5));
                user.getStatus().addConsumption(new Consumption("Stella", 5, 5));
                user.getStatus().addConsumption(new Consumption("Staropramen", 5, 5));

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

                user = new User();
                user.setName("Third User");
                user.setWeight(70);
                tour.addUser(user);

                tour.setEnd(end);
            }
            Bartour activeTour = new Bartour();
            activeTour.setName("Active Tour");

            addBartour(activeTour);

            User user = new User();
            user.setName("John");
            user.setWeight(70);
            activeTour.addUser(user);

            user = new User();
            user.setName("Wayne");
            user.setWeight(70);
            activeTour.addUser(user);

            EstablishmentLocationChronicleEvent e = new EstablishmentLocationChronicleEvent();
            e.setType("Bar");
            e.setLatitude(100);
            e.setLongitude(100);
            e.setLocationName("Kennedy's");
            activeTour.getChronicle().addEvent(e);

        }
    }

    public List<Bartour> getBartours(){
        return Collections.unmodifiableList(bartours);
    }

    public Bartour getActiveBartour(){
        return activeBartour;
    }

    public void addBartour(final Bartour bartour) {
        bartours.add(bartour);
        activeBartour = bartour;
        activeBartour.setOnFinishedListener(new Bartour.OnFinishedListener() {
            @Override
            public void OnFinished() {
                BartourChronicleEvent event = new BartourChronicleEvent(bartour.getName(), false);
                bartour.getChronicle().addEvent(event);
                activeBartour = null;
            }
        });
        BartourChronicleEvent event = new BartourChronicleEvent(bartour.getName(), true);
        bartour.getChronicle().addEvent(event);
    }

    public boolean removeBartour(Bartour bartour){
        return bartours.remove(bartour);
    }

}
