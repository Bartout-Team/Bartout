package ch.zhaw.bartout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by bwa on 29.03.2015.
 */
public class Ranking implements Serializable {

    private ArrayList<User> users;

    public Ranking(ArrayList<User> users){
        this.users = users;
    }

    public ArrayList<User> getRanking(){
        ArrayList<User> orderedUsers = new ArrayList<User>(users);
        Collections.sort(users,new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Double.compare(user1.getStatus().getAlcoholLevel(),user2.getStatus().getAlcoholLevel());
            }
        });
        return orderedUsers;
    }
}
