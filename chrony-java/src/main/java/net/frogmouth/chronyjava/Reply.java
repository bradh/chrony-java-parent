package net.frogmouth.chronyjava;

import java.util.HashMap;
import java.util.Map;

/**
 * Reply type.
 *
 * <p>Chrony supports a wide range of replies, which are identified by integer and encoded as a
 * uint16_t (in C). Not all of those reply types are yet supported.
 */
public enum Reply {
    Null(1),
    Tracking(5);

    private static final Map<Integer, Reply> lookupTable = new HashMap<>();

    static {
        for (Reply reply : values()) {
            lookupTable.put(reply.code, reply);
        }
    }

    /**
     * Look up the reply by integer identifier.
     *
     * @param identifier the integer value identifying the reply
     * @return the enumeration value corresponding to the {@code identifier}, or the {@code Null}
     *     enumeration value if the reply is not known.
     */
    public static Reply fromIndex(int identifier) {
        return lookupTable.getOrDefault(identifier, Null);
    }

    private final int code;

    private Reply(int c) {
        this.code = c;
    }

    public int getCode() {
        return code;
    }
}
