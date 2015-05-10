package ch.zhaw.bartout.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RankingIntegrationTest {

    private List<User> users;
    private Ranking descendingRanking;
    private Ranking ascendingRanking;

    @Before
    public void setUp() {
        users = new ArrayList<User>();
        users.add(new User());
        users.add(new User());
        descendingRanking = new Ranking(users, true);
        ascendingRanking = new Ranking(users, false);
    }

    @Test
    public void testUpdateDescendingRanking() throws Exception {
        descendingRanking.updateRanking();
        descendingRanking.getRanking().get(1).setAlcoholLevelInPercent(2.0);
        assertEquals(2, descendingRanking.getRanking().get(0).getAlcoholLevelInPercent(), 0);
    }

    @Test
    public void testUpdateAscendingRanking() throws Exception {
        ascendingRanking.updateRanking();
        ascendingRanking.getRanking().get(0).setAlcoholLevelInPercent(2.0);
        assertEquals(0, ascendingRanking.getRanking().get(1).getAlcoholLevelInPercent(), 0);
    }
}