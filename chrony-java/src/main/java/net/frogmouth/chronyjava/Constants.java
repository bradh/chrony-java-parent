package net.frogmouth.chronyjava;

/** Package-wide constants. */
public class Constants {

    /**
     * The packet is for a command request.
     *
     * <p>Requests go from client to server.
     */
    public static final byte PACKET_TYPE_COMMAND_REQUEST = 0x01;
    /**
     * The packet is for a command reply.
     *
     * <p>Replies go from server to client.
     */
    public static final byte PACKET_TYPE_COMMAND_REPLY = 0x02;

    /** Request reserved value 1. */
    static final byte REQUEST_RES1 = 0x00;
    /** Request reserved value 2. */
    static final byte REQUEST_RES2 = 0x00;
    /** Reply reserved value 1. */
    static final int REPLY_RES1_LENGTH = 1;
    /** Reply reserved value 2. */
    static final byte REPLY_RES2_LENGTH = 1;
    /** Reply pad value 1. */
    static final byte REPLY_PAD1_LENGTH = 2;
    /** Reply pad value 2. */
    static final byte REPLY_PAD2_LENGTH = 2;
    /** Reply pad value 3. */
    static final byte REPLY_PAD3_LENGTH = 2;

    /** Request pad 1 value. */
    static final byte[] REQUEST_PAD1 = new byte[] {0x00, 0x00, 0x00, 0x00};

    /** Request pad 2 value. */
    static final byte[] REQUEST_PAD2 = new byte[] {0x00, 0x00, 0x00, 0x00};

    /** The default Command and Monitoring UDP port. */
    public static final int DEFAULT_CANDM_PORT = 323;
}
