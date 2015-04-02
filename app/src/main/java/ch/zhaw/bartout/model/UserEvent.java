package ch.zhaw.bartout.model;

import java.io.Serializable;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class UserEvent extends Event {

    private User user;

    public User getUser() { return user;    }

    public void setUser(User user) {    this.user = user;   }
}
