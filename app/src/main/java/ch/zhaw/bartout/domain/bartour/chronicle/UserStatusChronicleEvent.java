package ch.zhaw.bartout.domain.bartour.chronicle;

import ch.zhaw.bartout.domain.bartour.user.User;
import ch.zhaw.bartout.domain.bartour.user.UserStatus;

/**
 * The class manages all changes of User to display it at the chronicle.
 */
public abstract class UserStatusChronicleEvent extends UserChronicleEvent {

    /**
     * Creates a UserStatusChronicleEvent with a User
     * @param user User for which the Event was created.
     */
    public UserStatusChronicleEvent(User user){
        super(user);
        status = user.getStatus();
    }

    private UserStatus status;

    public void setStatus(UserStatus status) {  this.status = status;   }

    public UserStatus getStatus() { return status;  }
}
