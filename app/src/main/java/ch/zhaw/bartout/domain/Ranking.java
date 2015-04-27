package ch.zhaw.bartout.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by bwa on 29.03.2015.
 */
public class Ranking implements Serializable {

    private List<User> users;
    private List<RankingUser> orderedUsers;

    public Ranking(List<User> users){
        this.users = users;
        this.orderedUsers = new ArrayList<RankingUser>();
    }

    public void updateRanking(){
        orderedUsers.clear();
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
    }

    public List<RankingUser> getRanking(){
        updateRanking();
        return orderedUsers;
    }
}
