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

    public ArrayList<RankingUser> getRanking(){
        ArrayList<RankingUser> orderedUsers = new ArrayList();
        double highestAlcoholLevel = 0;
        for (User user: users){
            if (highestAlcoholLevel<user.getStatus().getAlcoholLevel()){
                highestAlcoholLevel = user.getStatus().getAlcoholLevel();
            }
            orderedUsers.add(new RankingUser(user));
        }
        for(RankingUser user: orderedUsers){
            user.setAlcoholLevelInPercent(user.getUser().getStatus().getAlcoholLevel()/highestAlcoholLevel);
        }
        Collections.sort(orderedUsers);
        return orderedUsers;
    }
}
