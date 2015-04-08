package ch.zhaw.bartout.model;

/**
 * Created by Nico on 31.03.2015.
 */
public class UserParticipationChronicleEvent extends UserChronicleEvent {

    private boolean isNewUser;

    public void setNewUser(boolean isNewUser) { this.isNewUser = isNewUser; }

    public boolean isNewUser() {    return isNewUser;   }
}
