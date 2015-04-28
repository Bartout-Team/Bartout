package ch.zhaw.bartout.domain;

/**
 * Created by Nico on 31.03.2015.
 */
public class UserParticipationChronicleEvent extends UserChronicleEvent {

    public UserParticipationChronicleEvent(User user, boolean isNewUser){
        super(user);
        this.isNewUser=isNewUser;
    }

    private boolean isNewUser;

    public boolean isNewUser() {    return isNewUser;   }

    @Override
    public String getDisplayName() {
        return null;
    }
}
