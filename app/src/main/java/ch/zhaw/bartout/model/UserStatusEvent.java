package ch.zhaw.bartout.model;

/**
 * Created by Nico on 31.03.2015.
 */
public class UserStatusEvent extends UserEvent {

    private UserStatus status;

    public void setStatus(UserStatus status) {  this.status = status;   }

    public UserStatus getStatus() { return status;  }
}
