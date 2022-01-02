package net.frogmouth.chronyjava;

import static org.testng.Assert.*;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.testng.annotations.Test;

/** Unit tests for TimeSpec. */
public class TimeSpecTest {

    public TimeSpecTest() {}

    @Test
    public void checkFromBytes() {
        TimeSpec uut =
                TimeSpec.fromBytes(
                        new byte[] {
                            (byte) 0x00,
                            (byte) 0x00,
                            (byte) 0x00,
                            (byte) 0x00,
                            (byte) 0x61,
                            (byte) 0xcd,
                            (byte) 0x4c,
                            (byte) 0xfa,
                            (byte) 0x2f,
                            (byte) 0x10,
                            (byte) 0x33,
                            (byte) 0x4e
                        });
        assertEquals(uut.getSeconds(), 1640844538L);
        assertEquals(uut.getNanoseconds(), 789590862L);
        assertEquals(
                uut.getZonedDateTime(),
                ZonedDateTime.of(2021, 12, 30, 6, 8, 58, 789590862, ZoneOffset.UTC));
    }
}
