package net.frogmouth.chronyjava;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Random;

/** Generic request for information. */
public class Request {
    private static final byte PROTOCOL_VERSION_6 = 0x06;

    private byte protocolVersion = PROTOCOL_VERSION_6;
    private Command command = Command.Null;
    private int attempt = 0;
    private int sequenceNumber;

    public Request() {
        Random random = new Random();
        sequenceNumber = random.nextInt();
    }

    public byte getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(byte protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(protocolVersion);
        baos.write(Constants.PACKET_TYPE_COMMAND_REQUEST);
        baos.write(Constants.REQUEST_RES1);
        baos.write(Constants.REQUEST_RES2);
        baos.write(command.getBytes());
        baos.write(uint16ToBytes(attempt));
        baos.write(intToBytes(sequenceNumber));
        baos.write(Constants.REQUEST_PAD1);
        baos.write(Constants.REQUEST_PAD2);
        baos.write(command.getRequestPadding());
        return baos.toByteArray();
    }

    public byte[] sendRequest(InetAddress address, int port) throws IOException {
        byte[] request = toBytes();
        DatagramPacket packet = new DatagramPacket(request, request.length, address, port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        packet = new DatagramPacket(request, request.length);
        socket.receive(packet);
        return packet.getData();
    }

    public byte[] sendRequest() throws IOException {
        return this.sendRequest(InetAddress.getLocalHost(), Constants.DEFAULT_CANDM_PORT);
    }

    private byte[] uint16ToBytes(int v) {
        if ((v < 0) || (v > 65535)) {
            throw new IllegalArgumentException("out of range");
        }
        return ByteBuffer.allocate(2).putShort((short) v).array();
    }

    private byte[] intToBytes(int v) {
        return ByteBuffer.allocate(4).putInt(v).array();
    }
}
