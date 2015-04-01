package ch.zhaw.bartout.model;


import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by srueg on 29.03.15.
 */
public class Bartour {

    private String name;
    private ArrayList<User> users;
    private Ranking ranking;
    private Chronicle chronicle;
    private Calendar start;
    private Calendar end;

    public Bartour(){
        users = new ArrayList<>();
        users.add(new User().setName("First User"));
        users.add(new User().setName("Second User"));
        start = Calendar.getInstance();
    }

    public Bartour setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }

    public Bartour setEnd(Calendar end){
        this.end = end;
        return this;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Returns duration of Bartour or -1 if Bartour is not finish yet.
     * @return Duration in seconds, -1 if Bartour is not finish yet.
     */
    public long getDuration(){
        if(end != null) {
            return (end.getTimeInMillis() / 1000) - (start.getTimeInMillis() / 1000);
        }else{
            return -1;
        }
    }

}
