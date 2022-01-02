package net.frogmouth.chronyjava;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Representation of {@code timespec}.
 *
 * <p>This is a standard structure in C libraries. It represents a UTC time in seconds and
 * nanoseconds-of-second.
 */
public class TimeSpec {
    private long seconds;
    private long nanoseconds;

    /**
     * Create a new TimeSpec instance from a byte array.
     *
     * @param bytes byte array, must be 12 bytes.
     * @return the corresponding TimeSpec instance, initialised from {@code} bytes.
     */
    public static TimeSpec fromBytes(byte[] bytes) {
        TimeSpec timeSpec = new TimeSpec();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        timeSpec.setSeconds(bb.getLong());
        long seconds = bb.getInt(8) & 0x00FFFFFFFFl;
        timeSpec.setNanoseconds(seconds);
        return timeSpec;
    }

    /**
     * Get the number of seconds after the Epoch.
     *
     * @return seconds after the UTC epoch of 1970-01-01T00:00:00.
     */
    public long getSeconds() {
        return seconds;
    }

    /**
     * Set the number of seconds after the Epoch.
     *
     * @param seconds seconds after the UTC epoch of 1970-01-01T00:00:00.
     */
    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    /**
     * Get the number of nanoseconds of the second.
     *
     * @return nanoseconds-of-second after the UTC epoch of 1970-01-01T00:00:00.
     */
    public long getNanoseconds() {
        return nanoseconds;
    }

    /**
     * Set the number of nanoseconds of the second.
     *
     * @param nanoseconds nanoseconds-of-second after the UTC epoch of 1970-01-01T00:00:00.
     */
    public void setNanoseconds(long nanoseconds) {
        this.nanoseconds = nanoseconds;
    }

    public ZonedDateTime getZonedDateTime() {
        Instant instant = Instant.ofEpochSecond(seconds, nanoseconds);
        return ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
