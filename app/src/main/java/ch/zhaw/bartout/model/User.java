package ch.zhaw.bartout.model;

import java.io.Serializable;

/**
 * Created by bwa on 29.03.2015.
 */
public class User implements Serializable {
    private String name;
    private boolean man;
    private int weight;
    private UserStatus status;


    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public UserStatus getStatus() {
        return status;
    }

}
