package ch.zhaw.bartout.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


/**
 * Created by srueg on 29.03.15.
 */
public class Bartour implements Serializable {

    private String name;
    private List<User> users;
    private Ranking ranking;
    private Ranking driveFitnessRanking;
    private Chronicle chronicle;
    private Calendar start;
    private Calendar end;
    private transient OnFinishedListener finishedListener;

    public Bartour(){
        users = new ArrayList<User>();
        start = Calendar.getInstance();
        end = null;
        ranking = new Ranking(users, true);
        driveFitnessRanking = new Ranking(users, false);
        chronicle = new Chronicle();
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
        if(finishedListener != null) {
            finishedListener.OnFinished();
        }
        return this;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Fügt den Benutzer der Benutzerliste hinzu und updatet die Klasse Ranking
     * @param user
     */
    public void addUser(User user) {
        users.add(user);
        ranking.updateRanking();
        getDriveFitnessRanking().updateRanking();
        addUserParticipationChronicleEvent(user.copy(),true);
    }

    public boolean removeUser(User user) {
        boolean b = users.remove(user);
        ranking.updateRanking();
        getDriveFitnessRanking().updateRanking();
        addUserParticipationChronicleEvent(user.copy(),false);
        return b;
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

    public boolean getIsActive(){
        return end == null;
    }

    public void setOnFinishedListener(OnFinishedListener finishedListener){
        this.finishedListener = finishedListener;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public Chronicle getChronicle(){
        return chronicle;
    }

    private void addUserParticipationChronicleEvent(User user, boolean isNewUser){
        chronicle.addEvent(new UserParticipationChronicleEvent(user,isNewUser));
    }

    public Ranking getDriveFitnessRanking() {
        return driveFitnessRanking;
    }

    interface OnFinishedListener{
        public void OnFinished();
    }
    
    @Override
    public boolean equals(Object other){
        if(! (other instanceof Bartour)) return false;
        Bartour otherTour = (Bartour)other;
        if(!name.equals(otherTour.getName())) return false;
        if(start != null && !start.equals(otherTour.getStart())) return false;
        if(end != null && !end.equals(otherTour.getEnd())) return false;

        return true;
    }
}

