package ch.zhaw.bartout.domain;

import org.junit.Assert;

import static org.mockito.Mockito.*;
import java.util.Calendar;
import java.util.List;

import ch.zhaw.bartout.domain.User;

public class BartoutTest {

    @Test
    public void testGetInstance() throws Exception {
        Bartout one = Bartout.getInstance();
        Bartout two = Bartout.getInstance();
        Assert.assertSame("Check if Instance 'one' is same referance as Instance 'two'", one, two);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetBartours() throws Exception {
        List<Bartour> tours = Bartout.getInstance().getBartours();
        tours.clear();
    }

    @Test
    public void testAddRemoveBartour(){
        Bartout b = Bartout.getInstance();
        Bartour tour = new Bartour();
        b.addBartour(tour);
        Assert.assertFalse("Check, if there is exactly one Bartour", b.getBartours().isEmpty());
        Assert.assertSame("Check, if active Bartour references my previously created Bartour", tour, b.getActiveBartour());
        // Set Bartour to End
        tour.setEnd(Calendar.getInstance());
        Assert.assertEquals("Check, if there is no active Bartour anymore", null, b.getActiveBartour());
        Assert.assertTrue("Check, if remove returns true, so it found the Tour", b.removeBartour(tour));
        Assert.assertTrue("Check, if Bartour was removed from List", b.getBartours().isEmpty());
    }
}