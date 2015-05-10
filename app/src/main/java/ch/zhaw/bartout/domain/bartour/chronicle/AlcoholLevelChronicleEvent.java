package ch.zhaw.bartout.domain.bartour.chronicle;

/**
 * Created by serge on 27.04.2015.
 */

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.bartour.user.User;

/**
 * Snapshot of the specific integer alcohol level of a user
 * throwed when the user has a integer alcohol level
 */
public class AlcoholLevelChronicleEvent extends UserStatusChronicleEvent {

    private int level;

    public AlcoholLevelChronicleEvent(User user, int level) {
        super(user);
        this.level = level;
    }

    @Override
    public String getDisplayName() {
        return String.format("<b>%s</b> hat %d ‰",getUser().getName(),level);
    }

    @Override
    public View getView(Context context){
        View view = super.getView(context);

        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_alcohol));

        return view;
    }
}
