package ch.zhaw.bartout.domain.bartour.chronicle;

import ch.zhaw.bartout.domain.bartour.user.User;

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
