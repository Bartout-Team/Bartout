package ch.zhaw.bartout.domain;

/**
 * Created by Nico on 31.03.2015.
 */
public class UserStatusChronicleEvent extends UserChronicleEvent {

    public UserStatusChronicleEvent(User user){
        super(user);
        status = user.getStatus();
    }

    private UserStatus status;

    public void setStatus(UserStatus status) {  this.status = status;   }

    public UserStatus getStatus() { return status;  }
}
