package ch.zhaw.bartout.domain;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class UserChronicleEvent extends ChronicleEvent {

    public UserChronicleEvent(User user){
        super();
        this.user = user;
    }

    private User user;

    public User getUser() { return user;    }

}
