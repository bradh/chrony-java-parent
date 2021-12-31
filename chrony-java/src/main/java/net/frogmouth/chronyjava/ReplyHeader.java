package net.frogmouth.chronyjava;

public class ReplyHeader {

    private short version;
    private short packetType; // enum?
    private Command command;
    private Reply reply;
    private Status status;
    private int sequenceNumber;

    static ReplyHeader fromBytes(byte[] bytes) {
        ReplyHeader replyHeader = new ReplyHeader();
        int index = 0;
        replyHeader.setVersion(bytes[index]);
        index += Byte.BYTES;
        replyHeader.setPacketType(bytes[index]);
        index += Byte.BYTES;
        index += Constants.REPLY_RES1_LENGTH;
        index += Constants.REPLY_RES2_LENGTH;
        int command = ParseUtils.readUint16(bytes, index);
        index += Short.BYTES;
        replyHeader.setCommand(Command.fromIndex(command));
        int reply = ParseUtils.readUint16(bytes, index);
        index += Short.BYTES;
        replyHeader.setReply(Reply.fromIndex(reply));
        int status = ParseUtils.readUint16(bytes, index);
        index += Short.BYTES;
        replyHeader.setStatus(Status.fromIndex(status));
        index += Constants.REPLY_PAD1_LENGTH;
        index += Constants.REPLY_PAD2_LENGTH;
        index += Constants.REPLY_PAD3_LENGTH;
        int seq = ParseUtils.readUint32(bytes, index);
        replyHeader.setSequenceNumber(seq);
        // Everything after this in the header is padding
        return replyHeader;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public short getPacketType() {
        return packetType;
    }

    public void setPacketType(short packetType) {
        this.packetType = packetType;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
