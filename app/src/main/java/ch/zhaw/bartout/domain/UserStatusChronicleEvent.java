package ch.zhaw.bartout.domain;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import ch.zhaw.bartout.R;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class UserStatusChronicleEvent extends UserChronicleEvent {

    public UserStatusChronicleEvent(User user){
        super(user);
        status = user.getStatus();
    }

    private UserStatus status;

    public void setStatus(UserStatus status) {  this.status = status;   }

    public UserStatus getStatus() { return status;  }
}
