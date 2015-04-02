package ch.zhaw.bartout.model;

import java.io.Serializable;

/**
 * Created by Nico on 31.03.2015.
 */
public class RankingEvent extends Event {

    private Ranking ranking;

    public Ranking getRanking() {   return ranking; }

    public void setRanking(Ranking ranking) {   this.ranking = ranking; }
}
