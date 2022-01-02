/*
 */
package net.frogmouth.chronyjava;

import static org.testng.Assert.*;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.testng.annotations.Test;

/** Unit tests for Tracking class */
public class TrackingTest {

    public TrackingTest() {}

    @Test
    public void checkTrackingParse() throws IOException {
        byte[] bytes =
                new byte[] {
                    (byte) 0x06,
                    (byte) 0x02,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x21,
                    (byte) 0x00,
                    (byte) 0x05,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x72,
                    (byte) 0x8f,
                    (byte) 0xf6,
                    (byte) 0x22,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0xcb,
                    (byte) 0x0e,
                    (byte) 0x00,
                    (byte) 0xfb,
                    (byte) 0xcb,
                    (byte) 0x0e,
                    (byte) 0x00,
                    (byte) 0xfb,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x01,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x00,
                    (byte) 0x03,
                    (byte) 0x00,
                    (byte) 0x00,
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
                    (byte) 0x4e,
                    (byte) 0xec,
                    (byte) 0xb2,
                    (byte) 0xc6,
                    (byte) 0x04,
                    (byte) 0xe7,
                    (byte) 0x5a,
                    (byte) 0x71,
                    (byte) 0x8d,
                    (byte) 0xec,
                    (byte) 0x8b,
                    (byte) 0x3f,
                    (byte) 0x70,
                    (byte) 0x0f,
                    (byte) 0x7b,
                    (byte) 0x42,
                    (byte) 0xf4,
                    (byte) 0xf7,
                    (byte) 0x64,
                    (byte) 0xfc,
                    (byte) 0x96,
                    (byte) 0xfe,
                    (byte) 0xc4,
                    (byte) 0xeb,
                    (byte) 0x4d,
                    (byte) 0xf8,
                    (byte) 0x90,
                    (byte) 0x86,
                    (byte) 0x75,
                    (byte) 0xf2,
                    (byte) 0xa3,
                    (byte) 0x11,
                    (byte) 0x94,
                    (byte) 0x16,
                    (byte) 0x81,
                    (byte) 0x4f,
                    (byte) 0x69
                };
        Tracking uut = Tracking.fromBytes(bytes);
        assertNotNull(uut);
        assertEquals(uut.getReplyHeader().getVersion(), 6);
        assertEquals(uut.getReplyHeader().getPacketType(), 2);
        assertEquals(uut.getReplyHeader().getCommand(), Command.Tracking);
        assertEquals(uut.getReplyHeader().getReply(), Reply.Tracking);
        assertEquals(uut.getReplyHeader().getSequenceNumber(), 0x728FF622);
        /*
         * 0000 06 02 00 00 00 21 00 05 00 00 00 00 00 00 00 00
         * 0010 72 8f f6 22 00 00 00 00 00 00 00 00 cb 0e 00 fb
         * 0020 cb 0e 00 fb 00 00 00 00 00 00 00 00 00 00 00 00
         * 0030 00 01 00 00 00 03 00 00 00 00 00 00 61 cd 4c fa
         * 0040 2f 10 33 4e ec b2 c6 04 e7 5a 71 8d ec 8b 3f 70
         * 0050 0f 7b 42 f4 f7 64 fc 96 fe c4 eb 4d f8 90 86 75
         * 0060 f2 a3 11 94 16 81 4f 69
         *
         * Reference ID : CB0E00FB (toc.ntp.telstra.net)
         * Stratum : 3
         * Ref time (UTC) : Thu Dec 30 06:08:58 2021
         * System time : 0.000340983 seconds slow of NTP time
         * Last offset : -0.000039472 seconds
         * RMS offset : 0.000265594 seconds
         * Frequency : 33.185 ppm slow
         * Residual freq : -0.009 ppm
         * Skew : 0.192 ppm
         * Root delay : 0.017642239 seconds
         * Root dispersion : 0.002488230 seconds
         * Update interval : 517.2 seconds
         * Leap status : Normal
         */
        assertEquals(uut.getRefId(), 0xCB0E00FB);
        assertEquals(uut.getStratum(), 3);
        assertEquals(uut.getLeapStatus(), 0);
        assertEquals(
                uut.getTimeSpec().getZonedDateTime(),
                ZonedDateTime.of(2021, 12, 30, 6, 8, 58, 789590862, ZoneOffset.UTC));
        assertEquals(uut.getCurrentCorrection(), 0.000340983, 0.000000001);
        assertEquals(uut.getLastOffset(), -0.000039472, 0.000000001);
        assertEquals(uut.getRmsOffset(), 0.000265594, 0.000000001);
        assertEquals(uut.getFreqPPM(), -33.185, 0.001);
        assertEquals(uut.getResidFreqPPM(), -0.009, 0.001);
        assertEquals(uut.getSkewPPM(), 0.192, 0.001);
        assertEquals(uut.getRootDelay(), 0.017642239, 0.000000001);
        assertEquals(uut.getRootDispersion(), 0.002488230, 0.000000001);
        assertEquals(uut.getLastUpdateInterval(), 517.2, 0.1);
    }
}
