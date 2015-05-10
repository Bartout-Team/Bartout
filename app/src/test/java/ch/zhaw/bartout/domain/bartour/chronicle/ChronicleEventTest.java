package ch.zhaw.bartout.domain.bartour.chronicle;

import org.junit.Test;

import ch.zhaw.bartout.domain.bartour.chronicle.ATMLocationChronicleEvent;
import ch.zhaw.bartout.domain.bartour.chronicle.ChronicleEvent;
import ch.zhaw.bartout.domain.bartour.chronicle.EstablishmentLocationChronicleEvent;

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