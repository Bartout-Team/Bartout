package ch.zhaw.bartout.domain.bartour.chronicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.zhaw.bartout.domain.Bartout;

/**
 * Created by bwa on 29.03.2015.
 */
public class Chronicle implements Serializable {
    private List<ChronicleEvent> chronicleEvents;

    public Chronicle() {
        chronicleEvents = new ArrayList<ChronicleEvent>();
    }

    /**
     * Add new ChronicleEvent
     * @param event ChronicleEvent to add
     */
    public void addEvent(ChronicleEvent event) {
        chronicleEvents.add(event);
    }

    /**
     * Remove ChronicleEvent
     * @param event ChronicleEvent to remove
     */
    public void removeEvent(ChronicleEvent event)  {
        if(!chronicleEvents.remove(event)){
            throw new Error("Event not found!");
        }
    }

    /**
     * Get ChronicleEvents of specific Type
     * @param type Type to filter
     * @return List of ChronicleEvents filtered by Type
     */
    public List<ChronicleEvent> getChronicleEvents(Class type){
        List<ChronicleEvent> eventsOfType = new ArrayList<ChronicleEvent>();
        for(ChronicleEvent chronicleEvent: chronicleEvents){
            if(type.isAssignableFrom(chronicleEvent.getClass())){
                eventsOfType.add(chronicleEvent);
            }
        }
        return Collections.unmodifiableList(eventsOfType);
    }

    public List<ChronicleEvent> getChronicleEvents() {
        Collections.sort(chronicleEvents);
        return Collections.unmodifiableList(chronicleEvents);
    }

    /**
     * Gets the Chronicle of the currently active Bartour
     * @return active Chronicle
     */
    public static Chronicle getActiveChronicle(){
        return Bartout.getInstance().getActiveBartour().getChronicle();
    }
}
