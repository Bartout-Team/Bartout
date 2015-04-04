package ch.zhaw.bartout.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bwa on 29.03.2015.
 */
public class Ranking implements Serializable {
    private ArrayList<User> users;

    public Ranking() {

    }

    public ArrayList<User> getRanking() {
        return users;
    }
}
