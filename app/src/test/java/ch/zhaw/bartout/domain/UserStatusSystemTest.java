package ch.zhaw.bartout.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by serge on 03.05.2015.
 */
public class UserStatusSystemTest {

    User userMan;
    User userWoman;

    @Before
    public void setUp() throws Exception {
        Bartout bartout = Bartout.getInstance();
        Bartour bartour = new Bartour();
        bartour.setName("My bartour");
        userMan = new User();
        userMan.setName("man");
        userMan.setWeight(65);
        userMan.setMan(true);
        userWoman = new User();
        userWoman.setName("woman");
        userWoman.setWeight(65);
        userWoman.setMan(false);
        bartour.addUser(userMan);
        bartour.addUser(userWoman);
        bartout.addBartour(bartour);
    }

    @Test
    public void testGetAlcoholLevelMan() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        userStatus.addConsumption(new Consumption("", 12, 7.5));
        assertEquals("After one bottle wine, a man have to have 1.28‰", 1.28, userStatus.getAlcoholLevel(), 0.01);
    }

    @Test
    public void testGetAlcoholLevelWoman() throws Exception {
        UserStatus userStatus = new UserStatus(userWoman);
        userStatus.addConsumption(new Consumption("", 12, 7.5));
        assertEquals("After one bottle wine, a woman have to have 1.15‰", 1.5,userStatus.getAlcoholLevel(),0.01);
    }

}