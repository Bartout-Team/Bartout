package ch.zhaw.bartout.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ChronicleEventTest {



    @Test
    public void testIntEstablishmentLocationChronicleEventInstanceofChronicleEvent() throws Exception {
        EstablishmentLocationChronicleEvent establishmentLocationChronicleEvent = new EstablishmentLocationChronicleEvent();
        assertTrue(establishmentLocationChronicleEvent instanceof ChronicleEvent);
    }

    @Test
    public void testIntATMLocationChronicleEventInstanceofChronicleEvent() throws Exception {
        ATMLocationChronicleEvent atmLocationChronicleEvent = new ATMLocationChronicleEvent();
        assertTrue(atmLocationChronicleEvent instanceof ChronicleEvent);
    }
}