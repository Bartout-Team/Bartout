package ch.zhaw.bartout.domain;

import java.io.Serializable;

import ch.zhaw.bartout.service.deepcopy.Copyable;
import ch.zhaw.bartout.service.deepcopy.DeepCopy;

/**
 * Created by bwa on 29.03.2015.
 */
public class User implements Serializable,Copyable<User> {
    private String name;
    private boolean man;
    private int weight;
    private UserStatus status;

    public User() {
        status = new UserStatus(this);
    }


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

    @Override
    public User copy() {
        return (User)DeepCopy.copy(this);
    }
}
