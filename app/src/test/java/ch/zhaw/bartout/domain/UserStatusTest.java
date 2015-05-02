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
        userWomanMock = mock(User.class);
        when(userWomanMock.getWeight()).thenReturn(65);
        when(userWomanMock.isMan()).thenReturn(false);

        activeChronicleMock = mock(Chronicle.class);
        when(activeChronicleMock.getChronicleEvents()).thenReturn(new ArrayList<ChronicleEvent>());

        mockStatic(Chronicle.class);
        when(Chronicle.getActiveChronicle()).thenReturn(activeChronicleMock);
    }

    @Test
    public void testFitToDriveFalse() throws Exception {
        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(new Consumption("", 5, 5));
        assertEquals(true, userStatus.fitToDrive());
/*        userStatus = new UserStatus(userWomanMock);
        userStatus.addConsumption(new Consumption("", 5, 6));
        assertFalse(userStatus.fitToDrive());*/
    }

    @Test
    public void testFitToDriveTrue() throws Exception {
/*        UserStatus userStatus = new UserStatus(userManMock);
        userStatus.addConsumption(new Consumption("", 5, 6));
        assertTrue(userStatus.fitToDrive());*/
    }

    @Test
    public void testGetAlcoholLevel() throws Exception {

    }

    @Test
    public void testFitToDriveDuration() throws Exception {

    }

    @Test
    public void testAddConsumption() throws Exception {

    }

    @Test
    public void testRemoveConsumption() throws Exception {

    }

}