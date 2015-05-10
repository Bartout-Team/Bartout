package ch.zhaw.bartout.domain;

import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.zhaw.bartout.domain.bartour.Bartour;
import ch.zhaw.bartout.domain.bartour.user.User;

public class BartourTest extends InstrumentationTestCase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetName() throws Exception {
        Bartour b = new Bartour();
        b.setName("Hans");
        String reality = b.getName();
        String expected = "Hans";
        assertEquals(expected, reality);
    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testGetStart() throws Exception {

    }

    @Test
    public void testGetEnd() throws Exception {

    }

    @Test
    public void testSetEnd() throws Exception {

    }

    @Test
    public void testGetUsers() throws Exception {

    }

    @Test
    public void testAddUser() throws Exception {

    }

    @Test
    public void testRemoveUser() throws Exception {
        List<User> users = new ArrayList<User>();
        Bartour b = new Bartour();
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        boolean r = b.removeUser(u1);
        boolean e = false;
        assertEquals(e, r);

        b.addUser(u1);
        users.add(u1);
        r = b.removeUser(u1);
        e = true;
        assertEquals(e, r);


        b.addUser(u1);
        b.addUser(u2);
        b.addUser(u3);



    }

    @Test
    public void testGetDuration() throws Exception {

    }

    @Test
    public void testGetIsActive() throws Exception {
        Bartour b = new Bartour();
        Boolean r = b.getIsActive();
        Boolean e = true;
        assertEquals(e, r);
        b.setEnd(Calendar.getInstance());
        r = b.getIsActive();
        e = false;
        assertEquals(e, r);

    }

    @Test
    public void testSetOnFinishedListener() throws Exception {

    }

    @Test
    public void testGetRanking() throws Exception {

    }

    @Test
    public void testGetChronicle() throws Exception {

    }
}