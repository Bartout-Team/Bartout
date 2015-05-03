package ch.zhaw.bartout.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by serge on 03.05.2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Chronicle.class)
public class UserStatusIntegrationTest {

    User userMan;
    User userWoman;
    Chronicle activeChronicleMock;

    @Before
    public void setUp() throws Exception {
        activeChronicleMock = mock(Chronicle.class);
        when(activeChronicleMock.getChronicleEvents()).thenReturn(new ArrayList<ChronicleEvent>());

        mockStatic(Chronicle.class);
        when(Chronicle.getActiveChronicle()).thenReturn(activeChronicleMock);

        userMan = new User();
        userMan.setMan(true);
        userMan.setWeight(65);

        userWoman = new User();
        userWoman.setMan(false);
        userWoman.setWeight(65);
    }

    @Test
    public void testFitToDriveFalse() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Check, if the status gives false back", false, userStatus.fitToDrive());
        userStatus = new UserStatus(userWoman);
        userStatus.addConsumption(new Consumption("", 5, 6));
        assertEquals("Check, if the status gives false back", false, userStatus.fitToDrive());
    }

    @Test
    public void testFitToDriveTrue() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        userStatus.addConsumption(new Consumption("", 5, 6));
        assertTrue("Check, if the status gives true back", userStatus.fitToDrive());
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
        assertEquals("After one bottle wine, a man have to have 1.28‰", 1.5,userStatus.getAlcoholLevel(),0.01);
    }

    @Test
    public void testGetAlcoholLevelNoDuration() throws Exception {
        UserStatus userStatusMan = new UserStatus(userMan);
        assertEquals("No consumptions, a man have to have 0‰", 0,userStatusMan.getAlcoholLevel(),0);
        UserStatus userStatusWoman = new UserStatus(userWoman);
        assertEquals("No consumptions, a man have to have 0‰", 0,userStatusWoman.getAlcoholLevel(),0);
        Consumption consumption = new Consumption("", 5, 15);
        userStatusMan.addConsumption(consumption);
        userStatusMan.removeConsumption(consumption);
        assertEquals("After added and removed one consumption, a man have to have 0‰", 0, userStatusMan.getAlcoholLevel(), 0);
    }

    @Test
    public void testFitToDriveDurationWoman() throws Exception {
        UserStatus userStatus = new UserStatus(userWoman);
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Should give back 5.04h", 5.04, (double)userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testFitToDriveDurationMan() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Should give back 3.85h", 3.85, (double) userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testFitToDriveDurationNoDuration() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        assertEquals("Nothing drunk, should give back 0h", 0, (double) userStatus.fitToDriveDuration() / 60 / 60, 0.01);
        Consumption consumption = new Consumption("", 5, 15);
        userStatus.addConsumption(consumption);
        userStatus.removeConsumption(consumption);
        assertEquals("Added and Removed consumption, Should give back 0h", 0, (double) userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testAddConsumptionCount() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Added one consumption", 1, userStatus.getConsumptions().size());
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Added one consumption", 2, userStatus.getConsumptions().size());
        userStatus.addConsumption(new Consumption("", 5, 15));
        Consumption consumption = new Consumption("", 5, 15);
        userStatus.addConsumption(consumption);
        assertEquals("Added two consumptions", 4, userStatus.getConsumptions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddConsumptionFailureNullValue() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        userStatus.addConsumption(null);
        assertEquals("Added one consumption", 1, userStatus.getConsumptions().size());
    }

    @Test
    public void testRemoveConsumption() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        Consumption consumption = new Consumption("", 5, 15);
        userStatus.addConsumption(consumption);
        userStatus.removeConsumption(consumption);
        assertEquals("Added and removed one consumption", 0, userStatus.getConsumptions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveConsumptionFailureNull() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        Consumption consumption = new Consumption("", 5, 15);
        userStatus.addConsumption(consumption);
        userStatus.removeConsumption(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveConsumptionFailureNotAdded() throws Exception {
        UserStatus userStatus = new UserStatus(userMan);
        Consumption consumption = new Consumption("", 5, 15);
        userStatus.removeConsumption(consumption);
    }
}