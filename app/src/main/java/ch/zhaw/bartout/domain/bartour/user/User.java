package ch.zhaw.bartout.domain.bartour.user;

import java.io.Serializable;

import ch.zhaw.bartout.service.deepcopy.Copyable;
import ch.zhaw.bartout.service.deepcopy.DeepCopy;

/**
 * The class User holdes all items of a user.
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

    /**
     * Make a deep copy of a User
     * @return deep copy of User
     */
    @Override
    public User copy() {
        User copiedUser = (User)DeepCopy.copy(this);
        return copiedUser;
    }
}
