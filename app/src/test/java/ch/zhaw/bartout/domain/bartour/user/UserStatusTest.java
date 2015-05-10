package ch.zhaw.bartout.domain.bartour.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Calendar;

import ch.zhaw.bartout.domain.bartour.chronicle.Chronicle;
import ch.zhaw.bartout.domain.bartour.chronicle.ChronicleEvent;
import ch.zhaw.bartout.domain.bartour.user.Consumption;
import ch.zhaw.bartout.domain.bartour.user.User;
import ch.zhaw.bartout.domain.bartour.user.UserStatus;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by serge on 01.05.2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Chronicle.class)
public class UserStatusTest {

    User userManMock;
    User userWomanMock;
    Chronicle activeChronicleMock;

    @Before
    public void setUp() throws Exception {
        userManMock = mock(User.class);
        when(userManMock.getWeight()).thenReturn(65);
        when(userManMock.isMan()).thenReturn(true);
        when(userManMock.copy()).thenReturn(userManMock);

        userWomanMock = mock(User.class);
        when(userWomanMock.getWeight()).thenReturn(65);
        when(userWomanMock.isMan()).thenReturn(false);
        when(userWomanMock.copy()).thenReturn(userWomanMock);

        activeChronicleMock = mock(Chronicle.class);
        when(activeChronicleMock.getChronicleEvents()).thenReturn(new ArrayList<ChronicleEvent>());

        mockStatic(Chronicle.class);
        when(Chronicle.getActiveChronicle()).thenReturn(activeChronicleMock);
    }

    private Consumption mockConsumption(String name, double alcoholStrengthInPercent, double alcoholVolumeInDl){
        Consumption consumptionMock = mock(Consumption.class);
        when(consumptionMock.getConsumptionTime()).thenReturn(Calendar.getInstance());
        when(consumptionMock.getName()).thenReturn(name);
        when(consumptionMock.getAlcoholicStrength()).thenReturn(alcoholStrengthInPercent);
        when(consumptionMock.getVolume()).thenReturn(alcoholVolumeInDl);
        return  consumptionMock;
    }

    @Test
    public void testFitToDriveFalse() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(mockConsumption("", 5, 15));
        assertEquals("Check, if the status gives false back", false, userStatus.fitToDrive());
        userStatus = new UserStatus(userWomanMock);
        userStatus.addConsumption(mockConsumption("", 5, 6));
        assertEquals("Check, if the status gives false back", false, userStatus.fitToDrive());
    }

    @Test
    public void testFitToDriveTrue() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(mockConsumption("", 5, 6));
        assertTrue("Check, if the status gives true back", userStatus.fitToDrive());
    }

    @Test
    public void testGetAlcoholLevelMan() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(mockConsumption("", 12, 7.5));
        assertEquals("After one bottle wine, a man have to have 1.28‰", 1.28, userStatus.getAlcoholLevel(), 0.01);
    }

    @Test
    public void testGetAlcoholLevelWoman() throws Exception {
        UserStatus userStatus = new UserStatus(userWomanMock);
        userStatus.addConsumption(mockConsumption("", 12, 7.5));
        assertEquals("After one bottle wine, a woman have to have 1.15‰", 1.5,userStatus.getAlcoholLevel(),0.01);
    }

    @Test
    public void testGetAlcoholLevelNoDuration() throws Exception {
        UserStatus userStatusMan = new UserStatus(userManMock);
        assertEquals("No consumptions, a man have to have 0‰", 0,userStatusMan.getAlcoholLevel(),0);
        UserStatus userStatusWoman = new UserStatus(userWomanMock);
        assertEquals("No consumptions, a man have to have 0‰", 0,userStatusWoman.getAlcoholLevel(),0);
        Consumption consumption = mockConsumption("", 5, 15);
        userStatusMan.addConsumption(consumption);
        userStatusMan.removeConsumption(consumption);
        assertEquals("After added and removed one consumption, a man have to have 0‰", 0, userStatusMan.getAlcoholLevel(), 0);
    }

    @Test
    public void testFitToDriveDurationWoman() throws Exception {
        UserStatus userStatus = new UserStatus(userWomanMock);
        userStatus.addConsumption(mockConsumption("", 5, 15));
        assertEquals("Should give back 5.04h", 5.04, (double)userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testFitToDriveDurationMan() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(mockConsumption("", 5, 15));
        assertEquals("Should give back 3.85h", 3.85, (double) userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testFitToDriveDurationNoDuration() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        assertEquals("Nothing drunk, should give back 0h", 0, (double) userStatus.fitToDriveDuration() / 60 / 60, 0.01);
        Consumption consumption = mockConsumption("", 5, 15);
        userStatus.addConsumption(consumption);
        userStatus.removeConsumption(consumption);
        assertEquals("Added and Removed consumption, Should give back 0h", 0, (double) userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testAddConsumptionCount() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(mockConsumption("", 5, 15));
        assertEquals("Added one consumption", 1, userStatus.getConsumptions().size());
        userStatus.addConsumption(mockConsumption("", 5, 15));
        assertEquals("Added one consumption", 2, userStatus.getConsumptions().size());
        userStatus.addConsumption(mockConsumption("", 5, 15));
        Consumption consumption = mockConsumption("", 5, 15);
        userStatus.addConsumption(consumption);
        assertEquals("Added two consumptions", 4, userStatus.getConsumptions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddConsumptionFailureNullValue() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(null);
        assertEquals("Added one consumption", 1, userStatus.getConsumptions().size());
    }

    @Test
    public void testRemoveConsumption() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        Consumption consumption = mockConsumption("", 5, 15);
        userStatus.addConsumption(consumption);
        userStatus.removeConsumption(consumption);
        assertEquals("Added and removed one consumption", 0, userStatus.getConsumptions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveConsumptionFailureNull() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        Consumption consumption = mockConsumption("", 5, 15);
        userStatus.addConsumption(consumption);
        userStatus.removeConsumption(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveConsumptionFailureNotAdded() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        Consumption consumption = mockConsumption("", 5, 15);
        userStatus.removeConsumption(consumption);
    }

}