package ch.zhaw.bartout.model;

import java.io.Serializable;

/**
 * Created by Nico on 31.03.2015.
 */
public class EstablishmentLocationEvent extends LocationEvent {
    private String type;

    public String getType() {   return type;   }

    public void setType(String type) {  this.type = type;   }

}
