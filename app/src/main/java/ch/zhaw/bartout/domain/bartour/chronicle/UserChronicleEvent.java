package ch.zhaw.bartout.domain.bartour.chronicle;

import ch.zhaw.bartout.domain.bartour.user.User;

/**
 * The class holds all chronicleEvents that are user-related.
 */
public abstract class UserChronicleEvent extends ChronicleEvent {

    public UserChronicleEvent(User user){
        super();
        this.user = user;
    }

    private User user;

    public User getUser() { return user;    }

}
