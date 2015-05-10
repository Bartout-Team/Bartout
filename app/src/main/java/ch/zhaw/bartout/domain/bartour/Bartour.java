package ch.zhaw.bartout.domain.bartour;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import ch.zhaw.bartout.domain.bartour.chronicle.Chronicle;
import ch.zhaw.bartout.domain.bartour.ranking.Ranking;
import ch.zhaw.bartout.domain.bartour.user.User;
import ch.zhaw.bartout.domain.bartour.chronicle.UserParticipationChronicleEvent;


/**
 * The class Bartour manages a Bartour. It holds a chronicle, a ranking and a list of users.
 */
public class Bartour implements Comparable<Bartour>,  Serializable {

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

    /**
     * Sets the End of the Bartour and calls OnFinished Listener
     * @param end Calendar obj for End of Bartout.
     * @return Bartour obj for chaining.
     */
    public Bartour setEnd(Calendar end){
        this.end = end;
        if(finishedListener != null) {
            finishedListener.OnFinished();
        }
        return this;
    }

    /**
     * Gets all Users in an unmodifiable List. Use add/removeUser to modify the list.
     * @return unmodifiable List of Users
     */
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Adds a user to the list of bartour users.
     * @param user The user added to the list.
     */
    public void addUser(User user) {
        users.add(user);
        ranking.updateRanking();
        getDriveFitnessRanking().updateRanking();
        addUserParticipationChronicleEvent(user.copy(),true);
    }

    /**
     * Removes a user from the list of bartour users.
     * @param user The user removed from the list.
     * @return True if user could be removed from list, false otherwise.
     */
    public boolean removeUser(User user) {
        boolean b = users.remove(user);
        ranking.updateRanking();
        getDriveFitnessRanking().updateRanking();
        addUserParticipationChronicleEvent(user.copy(),false);
        return b;
    }

    /**
     * Returns duration of Bartour since creation or -1 if Bartour is not finish yet.
     * @return Duration in seconds, -1 if Bartour is not finish yet.
     */
    public long getDuration(){
        if(end != null) {
            return (end.getTimeInMillis() / 1000) - (start.getTimeInMillis() / 1000);
        }else{
            return -1;
        }
    }

    /**
     * If the bartour is currently active.
     * @return True if the bartour is active, false otherwise.
     */
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

    /**
     * Adds a UserParticipationChronicleEvent to the chronicle.
     * @param user Which user the event will be created for.
     * @param isNewUser If it is a new user or not.
     */
    private void addUserParticipationChronicleEvent(User user, boolean isNewUser){
        chronicle.addEvent(new UserParticipationChronicleEvent(user,isNewUser));
    }

    public Ranking getDriveFitnessRanking() {
        return driveFitnessRanking;
    }

    public interface OnFinishedListener{
        /**
         * Gets called when a Bartour finished.
         */
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

    @Override
    public int compareTo(Bartour another) {
        return another.start.compareTo(start);
    }
}

