package ch.zhaw.bartout.domain;

/**
 * Created by Nico on 31.03.2015.
 */
public class ATMLocationChronicleEvent extends LocationChronicleEvent {
    @Override
    public String getDisplayName() {
        return "Bank beucht: " + getLocationName();
    }
}
