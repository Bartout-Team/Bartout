package ch.zhaw.bartout.domain.bartour.ranking;

import java.io.Serializable;

import ch.zhaw.bartout.domain.bartour.user.User;

/**
 * Created by serge on 06.04.2015.
 * Wrapper for the User class
 * This class adds the functionality, of alcoholLevel in percent (only needed on the ranking)
 */
public class RankingUser implements Comparable<RankingUser>, Serializable {
    private User user;
    private double alcoholLevelInPercent;

    /**
     * Constructor for RankingUser
     * @param user User obj, for which the RankingUser is created.
     */
    public RankingUser(User user){
        this.user = user;
    }

    public double getAlcoholLevelInPercent(){
        return alcoholLevelInPercent;
    }

    public void setAlcoholLevelInPercent(double alcoholLevel){
        alcoholLevelInPercent = alcoholLevel;
    }
    public User getUser(){
        return user;
    }

    @Override
    public int compareTo(RankingUser another) {
        return Double.compare(alcoholLevelInPercent,another.getAlcoholLevelInPercent());
    }
}
