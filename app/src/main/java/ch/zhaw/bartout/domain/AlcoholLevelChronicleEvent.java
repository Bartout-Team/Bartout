package ch.zhaw.bartout.domain;

/**
 * Created by serge on 27.04.2015.
 */
public class AlcoholLevelChronicleEvent extends UserStatusChronicleEvent {

    public AlcoholLevelChronicleEvent(User user) {
        super(user);

    }

    @Override
    public String getDisplayName() {
        return String.format("%s hat %d",getUser().getName(),getUser().getStatus().getAlcoholLevel());
    }
}
