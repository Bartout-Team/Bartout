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
 * Created by bwa on 29.03.2015.
 */
public class Ranking implements Serializable, Copyable<Ranking> {

    private List<User> users;
    private List<RankingUser> orderedUsers;
    private boolean descending;

    public Ranking(List<User> users, boolean descending){
        this.users = users;
        this.orderedUsers = new ArrayList<RankingUser>();
        this.descending = descending;
    }

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

    @Override
    public Ranking copy() {
        Ranking ranking = (Ranking) DeepCopy.copy(this);
        return ranking;
    }
}
