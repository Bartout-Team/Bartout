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

    public List<ChronicleEvent> getChronicleEvents() {
        return chronicleEvents;
    }

    /***
     * updatet die Liste, baut alles nochmals auf und sortiert nach Datum
     */
    public void updateChronicleEvents(){

    }
}
