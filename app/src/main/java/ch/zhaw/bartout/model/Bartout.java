package ch.zhaw.bartout.model;

import java.util.ArrayList;

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
        bartours.add(new Bartour().setName("First Tour"));
        bartours.add(new Bartour().setName("Second Tour"));
        bartours.add(new Bartour().setName("Third Tour"));
    }

    public ArrayList<Bartour> getBartours(){
        return bartours;
    }

}
