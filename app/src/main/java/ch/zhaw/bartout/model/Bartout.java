package ch.zhaw.bartout.model;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by srueg on 29.03.15.
 */
public class Bartout {

    // Singleton
    private static Bartout _instance;
    public static Bartout getInstance(){
        if(_instance == null){
            _instance = new Bartout();
        }
        return _instance;
    }
    // end Singleton

    private ArrayList<Bartour> bartours;

    protected Bartout(){
        bartours = new ArrayList<>();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.HOUR, 2);
        bartours.add(new Bartour().setName("First Tour").setEnd(end));
        bartours.add(new Bartour().setName("Second Tour").setEnd(end));
        bartours.add(new Bartour().setName("Third Tour").setEnd(end));
    }

    public ArrayList<Bartour> getBartours(){
        return bartours;
    }

}
