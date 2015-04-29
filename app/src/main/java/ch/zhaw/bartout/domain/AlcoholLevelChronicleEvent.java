package ch.zhaw.bartout.domain;

/**
 * Created by serge on 27.04.2015.
 */

/**
 * Snapshot of the specific integer alcohol level of a user
 * throwed when the user has a integer alcohol level
 */
public class AlcoholLevelChronicleEvent extends UserStatusChronicleEvent {

    private int level;

    public AlcoholLevelChronicleEvent(User user, int level) {
        super(user);
        this.level = level;
    }

    @Override
    public String getDisplayName() {
        return String.format("%s hat %d",getUser().getName(),level);
    }
}
