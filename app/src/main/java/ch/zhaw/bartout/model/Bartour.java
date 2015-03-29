package ch.zhaw.bartout.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by srueg on 29.03.15.
 */
public class Bartour {

    private String name;
    private Date start;
    private Date end;
    private ArrayList<User> users;
    private Ranking ranking;
    private Chronicle chronicle;

    public Bartour(){
        start = new Date();
    }

    public Bartour setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }


    public void setEnd(Date end) {
        this.end = end;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
