package ch.zhaw.bartout.model;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class UserChronicleEvent extends ChronicleEvent {

    private User user;

    public User getUser() { return user;    }

    public void setUser(User user) {    this.user = user;   }
}
