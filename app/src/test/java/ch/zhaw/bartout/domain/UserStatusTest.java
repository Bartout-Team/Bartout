package ch.zhaw.bartout.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

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

    @Test
    public void testFitToDriveFalse() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Check, if the status gives false back", false, userStatus.fitToDrive());
        userStatus = new UserStatus(userWomanMock);
        userStatus.addConsumption(new Consumption("", 5, 6));
        assertEquals("Check, if the status gives false back", false, userStatus.fitToDrive());
    }

    @Test
    public void testFitToDriveTrue() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(new Consumption("", 5, 6));
        assertTrue("Check, if the status gives true back", userStatus.fitToDrive());
    }

    @Test
    public void testGetAlcoholLevel() throws Exception {

    }

    @Test
    public void testFitToDriveDurationWoman() throws Exception {
        UserStatus userStatus = new UserStatus(userWomanMock);
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Should give back 5.04h", 5.04, (double)userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testFitToDriveDurationMan() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(new Consumption("", 5, 15));
        assertEquals("Should give back 3.85h", 3.85, (double)userStatus.fitToDriveDuration() / 60 / 60, 0.01);
    }

    @Test
    public void testFitToDriveDurationNoDuration() throws Exception {

    }

    @Test
    public void testAddConsumption() throws Exception {

    }

    @Test
    public void testRemoveConsumption() throws Exception {

    }

}