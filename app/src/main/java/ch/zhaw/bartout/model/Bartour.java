package ch.zhaw.bartout.model;

import java.util.Date;

/**
 * Created by srueg on 29.03.15.
 */
public class Bartour {

    private String name;
    private Date start;

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

    private Date end;

}
