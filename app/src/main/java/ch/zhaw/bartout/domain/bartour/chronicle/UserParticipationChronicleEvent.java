package ch.zhaw.bartout.domain.bartour.chronicle;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.bartour.user.User;

/**
 * The classe holds the entry or leaving of a User to display it at the chronicle.
 */
public class UserParticipationChronicleEvent extends UserChronicleEvent {

    /**
     * Creates a UserParticipationChronicleEvent with a User
     * @param user The User, for which the Event is created.
     * @param isNewUser true if a new User joined the Bartour, flase if a User left the Bartour
     */
    public UserParticipationChronicleEvent(User user, boolean isNewUser){
        super(user);
        this.isNewUser=isNewUser;
    }

    private boolean isNewUser;

    public boolean isNewUser() {    return isNewUser;   }

    @Override
    public String getDisplayName() {
        String s = "";
        if(isNewUser){
            s = "Neuer Benutzer: ";
        }else{
            s = "Benutzer geht: ";
        }
        s += "<b>"+getUser().getName()+"</b>";

        return s;
    }

    @Override
    public View getView(Context context){
        View view = super.getView(context);

        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_new_user));

        return view;
    }
}
