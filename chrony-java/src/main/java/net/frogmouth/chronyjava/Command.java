package net.frogmouth.chronyjava;

import java.util.HashMap;
import java.util.Map;

/**
 * Command type.
 *
 * <p>Chrony supports a wide range of requests, which are identified by integer and encoded as a
 * uint16_t (in C). Not all of those commands are yet supported.
 */
public enum Command {
    Null(0, 4),
    Tracking(33, net.frogmouth.chronyjava.Tracking.getPaddingBytes());

    private static final Map<Integer, Command> lookupTable = new HashMap<>();

    static {
        for (Command command : values()) {
            lookupTable.put(command.code, command);
        }
    }

    /**
     * Look up the command by integer identifier.
     *
     * @param identifier the integer value identifying the command
     * @return the enumeration value corresponding to the {@code identifier}, or the {@code Null}
     *     enumeration value if the command is not known.
     */
    public static Command fromIndex(int identifier) {
        return lookupTable.getOrDefault(identifier, Null);
    }

    private final int code;
    private final int paddingLength;

    private Command(int c, int padding) {
        this.code = c;
        this.paddingLength = padding;
    }

    public int getCode() {
        return code;
    }

    public byte[] getBytes() {
        // this is a hack, in the knowledge that the max code is currently 72.
        return new byte[] {0x00, (byte) this.code};
    }

    byte[] getRequestPadding() {
        return new byte[this.paddingLength];
    }
}
