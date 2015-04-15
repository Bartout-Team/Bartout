package ch.zhaw.bartout.domain;

/**
 * Created by Nico on 31.03.2015.
 */
public class UserStatusChronicleEvent extends UserChronicleEvent {

    private UserStatus status;

    public void setStatus(UserStatus status) {  this.status = status;   }

    public UserStatus getStatus() { return status;  }
}
