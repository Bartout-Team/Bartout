package ch.zhaw.bartout.domain.bartour.ranking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ch.zhaw.bartout.domain.bartour.chronicle.RankingChronicleEvent;
import ch.zhaw.bartout.domain.bartour.user.User;
import ch.zhaw.bartout.domain.bartour.chronicle.Chronicle;
import ch.zhaw.bartout.service.deepcopy.Copyable;
import ch.zhaw.bartout.service.deepcopy.DeepCopy;


/**
 * The class Ranking is responsible for the ranking of the alcohol-levels of all users of a bartour.
 */
public class Ranking implements Serializable, Copyable<Ranking> {

    private List<User> users;
    private List<RankingUser> orderedUsers;
    private boolean descending;

    /**
     * Constructor for Ranking
     * @param users Users which occure in the Ranking
     * @param descending true for descending, false for ascending
     */
    public Ranking(List<User> users, boolean descending){
        this.users = users;
        this.orderedUsers = new ArrayList<RankingUser>();
        this.descending = descending;
    }

    /**
     * Updates the Ranking based on new AlcoholLevels
     */
    public void updateRanking(){
        List<RankingUser> newOrderedList = new ArrayList<RankingUser>();

        double highestAlcoholLevel = 0;
        for (User user: users){
            if (highestAlcoholLevel<user.getStatus().getAlcoholLevel()){
                highestAlcoholLevel = user.getStatus().getAlcoholLevel();
            }
            newOrderedList.add(new RankingUser(user));
        }
        for(RankingUser user: newOrderedList){
            user.setAlcoholLevelInPercent(user.getUser().getStatus().getAlcoholLevel()/highestAlcoholLevel);
        }
        Collections.sort(newOrderedList);
        if(descending) {
            Collections.reverse(newOrderedList);
        }
        boolean listHasChanged = rankingHasChanged(orderedUsers, newOrderedList);
        if (listHasChanged){
            Chronicle.getActiveChronicle().addEvent(new RankingChronicleEvent(this.copy()));
        }
        orderedUsers.clear();
        orderedUsers.addAll(newOrderedList);
    }

    public List<RankingUser> getRanking(){
        updateRanking();
        return orderedUsers;
    }

    boolean rankingHasChanged(List<RankingUser> firstList, List<RankingUser> secondList){
        boolean listHasChanged = false;
        if (firstList.size()!=secondList.size()){
            //Change of the size is not interesting, we have already the participation event
            listHasChanged = false;
        }else{
            Iterator<RankingUser> secondListIterator = secondList.iterator();
            for(RankingUser rankingUser: firstList){
                RankingUser secondListUser = secondListIterator.next();
                listHasChanged = rankingUser.getUser()!=secondListUser.getUser();
                if(listHasChanged){
                    break;
                }
            }
        }
        return listHasChanged;
    }

    /**
     * Make a deep copy of a Ranking
     * @return deep copy of Ranking
     */
    @Override
    public Ranking copy() {
        Ranking ranking = (Ranking) DeepCopy.copy(this);
        return ranking;
    }
}
