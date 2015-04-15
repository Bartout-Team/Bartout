package ch.zhaw.bartout.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bwa on 29.03.2015.
 */
public class Chronicle implements Serializable {
    private List<ChronicleEvent> chronicleEvents;

    public Chronicle() {

    }

    public List<ChronicleEvent> getChronicleEvents() {
        return chronicleEvents;
    }

}
