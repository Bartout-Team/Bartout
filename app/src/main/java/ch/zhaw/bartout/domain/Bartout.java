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
        Calendar end = Calendar.getInstance();
        end.add(Calendar.HOUR, 2);

        addBartour(new Bartour().setName("First Tour").setEnd(end));
        addBartour(new Bartour().setName("Second Tour").setEnd(end));
        addBartour(new Bartour().setName("Third Tour").setEnd(end));
        activeBartour = null;
        //addBartour(new Bartour().setName("Active Tour"));

        for(Bartour tour : bartours){
            tour.addUser(new User().setName("First User"));
            tour.addUser(new User().setName("Second User"));
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