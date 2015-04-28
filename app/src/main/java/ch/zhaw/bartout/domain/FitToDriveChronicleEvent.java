package ch.zhaw.bartout.domain;

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
            return String.format("%s darf wieder fahren",getUser().getName());
        }else{
            return String.format("%s darf nicht mehr fahren",getUser().getName());
        }

    }

}
