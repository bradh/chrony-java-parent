package net.frogmouth.chronyjava;

class ParseUtils {
    private ParseUtils() {}

    static int readUint32(byte[] bytes, int index) {
        int v =
                ((bytes[index] << (Byte.SIZE * 3)) & 0xFF000000)
                        + ((bytes[index + 1] << (Byte.SIZE * 2)) & 0x00FF0000)
                        + ((bytes[index + 2] << (Byte.SIZE * 1)) & 0x0000FF00)
                        + ((bytes[index + 3]) & 0x000000FF);
        return v;
    }

    static int readUint16(byte[] bytes, int index) {
        return ((bytes[index] << Byte.SIZE) & 0xFF00) + (bytes[index + 1] & 0xFF);
    }
}
