package ch.zhaw.bartout.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwa on 29.03.2015.
 */
public class Chronicle implements Serializable {
    private List<ChronicleEvent> chronicleEvents;

    public Chronicle() {
        chronicleEvents = new ArrayList<ChronicleEvent>();
    }

    public void addEvent(ChronicleEvent event) {
        chronicleEvents.add(event);
    }

    public void removeEvent(ChronicleEvent event){
        chronicleEvents.remove(event);
    }

    public List<ChronicleEvent> getChronicleEvents(Class type){
        List<ChronicleEvent> eventsOfType = new ArrayList<ChronicleEvent>();
        for(ChronicleEvent chronicleEvent: chronicleEvents){
            if(chronicleEvent.getClass().isInstance(type)){
                eventsOfType.add(chronicleEvent);
            }
        }
        return eventsOfType;
    }

    public List<ChronicleEvent> getChronicleEvents() {
        return chronicleEvents;
    }

    public static Chronicle getActiveChronicle(){
        return Bartout.getInstance().getActiveBartour().getChronicle();
    }
}
