package net.frogmouth.chronyjava;

class ParseUtils {
    private static final long FLOAT_EXP_MASK = 0x7E000000L;
    private static final long FLOAT_EXP_SIGN_MASK = 0x80000000L;
    private static final long FLOAT_COEFF_MAGNITUDE_MASK = 0x00FFFFFFL;
    private static final long FLOAT_COEFF_SIGN_MASK = 0x01000000L;
    private static final int FLOAT_COEF_BITS = 25;

    static double ChronyFloatToJavaDouble(int chronyFloat) {
        long exponent = (chronyFloat & FLOAT_EXP_MASK) >> FLOAT_COEF_BITS;
        if ((chronyFloat & (FLOAT_EXP_SIGN_MASK)) == (FLOAT_EXP_SIGN_MASK)) {
            // Two's complement negative
            exponent = -1 * ((~(exponent) & 0x3F) + 1);
        }
        long coefficient = (chronyFloat & FLOAT_COEFF_MAGNITUDE_MASK);
        if ((chronyFloat & FLOAT_COEFF_SIGN_MASK) == FLOAT_COEFF_SIGN_MASK) {
            // Two's complement negative
            coefficient = -1 * ((~(coefficient) & FLOAT_COEFF_MAGNITUDE_MASK) + 1);
        }

        return coefficient * Math.pow(2.0, exponent - FLOAT_COEF_BITS);
    }

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
