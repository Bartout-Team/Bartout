package ch.zhaw.bartout.domain;

/**
 * Created by Nico on 31.03.2015.
 */
public class RankingChronicleEvent extends ChronicleEvent {

    private Ranking ranking;

    public Ranking getRanking() {   return ranking; }

    public void setRanking(Ranking ranking) {   this.ranking = ranking; }

    @Override
    public String getDisplayName() {
        return "Ranking";
    }
}
