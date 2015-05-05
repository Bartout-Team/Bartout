package ch.zhaw.bartout.domain;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import ch.zhaw.bartout.R;

/**
 * Created by Nico on 31.03.2015.
 */
public class UserParticipationChronicleEvent extends UserChronicleEvent {

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
