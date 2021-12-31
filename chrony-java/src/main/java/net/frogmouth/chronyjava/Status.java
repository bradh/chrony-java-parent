package net.frogmouth.chronyjava;

import java.util.HashMap;
import java.util.Map;

/**
 * Status type.
 *
 * <p>Chrony provides status in reply messages, which are identified by integer and encoded as a
 * uint16_t (in C). This enumeration maps the integer code to an enumeration value.
 */
public enum Status {
    Unknown(-1),
    Success(0),
    Failed(1),
    Unauth(2),
    Invalid(3),
    NoSuchSource(4),
    InvalidTS(5),
    NotEnabled(6),
    BadSubnet(7),
    AccessAllowed(8),
    AccessDenied(9),
    NoHostAccess(10),
    SourceAlreadyKnown(11),
    TooManySources(12),
    NoRTC(13),
    BadRTCFile(14),
    Inactive(15),
    BadSample(16),
    InvalidAF(17),
    BadPktVersion(18),
    BadPktLength(19),
    InvalidName(21);

    private static final Map<Integer, Status> lookupTable = new HashMap<>();

    static {
        for (Status status : values()) {
            lookupTable.put(status.code, status);
        }
    }

    /**
     * Look up the status by integer identifier.
     *
     * @param identifier the integer value identifying the status
     * @return the enumeration value corresponding to the {@code identifier}, or the {@code Unknown}
     *     enumeration value if the reply is not known.
     */
    public static Status fromIndex(int identifier) {
        return lookupTable.getOrDefault(identifier, Unknown);
    }

    private final int code;

    private Status(int c) {
        this.code = c;
    }

    public int getCode() {
        return code;
    }
}
