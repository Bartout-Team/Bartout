package ch.zhaw.bartout.domain;

/**
 * Created by serge on 27.04.2015.
 */
public class IntegerAlcoholVolumeChronicleEvent extends UserStatusChronicleEvent {

    public IntegerAlcoholVolumeChronicleEvent(User user) {
        super(user);

    }

    @Override
    public String getDisplayName() {
        return null;
    }
}
