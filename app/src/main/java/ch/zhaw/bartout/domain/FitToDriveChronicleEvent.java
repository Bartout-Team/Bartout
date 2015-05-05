package ch.zhaw.bartout.domain;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import ch.zhaw.bartout.R;

/**
 * Created by serge on 28.04.2015.
 */
public class FitToDriveChronicleEvent extends UserStatusChronicleEvent {
    private boolean isFitToDrive;
    public FitToDriveChronicleEvent(User user, boolean isFitToDrive) {
        super(user);
        this.isFitToDrive = isFitToDrive;
    }

    @Override
    public String getDisplayName() {
        if (isFitToDrive){
            return String.format("<b>%s</b> darf wieder fahren",getUser().getName());
        }else{
            return String.format("<b>%s</b> darf nicht mehr fahren",getUser().getName());
        }
    }

    @Override
    public View getView(Context context){
        View view = super.getView(context);

        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_car));

        return view;
    }
}
