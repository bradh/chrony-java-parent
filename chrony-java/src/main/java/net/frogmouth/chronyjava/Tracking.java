package net.frogmouth.chronyjava;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/** Chrony Tracking results. */
public class Tracking {
    private static final int FLOAT_EXP_BITS = 7;
    private static final int FLOAT_COEF_BITS = 32 - FLOAT_EXP_BITS;
    private ReplyHeader replyHeader;
    private int refId;
    private int stratum;
    private int leapStatus;
    private double currentCorrection;
    private double lastOffset;
    private double rmsOffset;
    private double freqPPM;
    private double residFreqPPM;
    private double skewPPM;
    private double rootDelay;
    private double rootDispersion;
    private double lastUpdateInterval;

    public static int getPaddingBytes() {
        return 84;
    }

    public static Tracking fromBytes(byte[] bytes) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            try (DataInputStream dis = new DataInputStream(bais)) {
                Tracking tracking = new Tracking();
                ReplyHeader replyHeader = ReplyHeader.fromBytes(dis.readNBytes(28));
                tracking.setReplyHeader(replyHeader);
                tracking.setRefId(dis.readInt());
                // TODO: parse properly.
                byte[] ipaddr = dis.readNBytes(20);
                tracking.setStratum(dis.readUnsignedShort());
                tracking.setLeapStatus(dis.readUnsignedShort());
                byte[] timespec = dis.readNBytes(12);
                int currentCorrection = dis.readInt();
                tracking.setCurrentCorrection(FloatToDouble(currentCorrection));
                int lastOffset = dis.readInt();
                tracking.setLastOffset(FloatToDouble(lastOffset));
                int rmsOffset = dis.readInt();
                tracking.setRmsOffset(FloatToDouble(rmsOffset));
                int freqPPM = dis.readInt();
                tracking.setFreqPPM(FloatToDouble(freqPPM));
                int residFreqPPM = dis.readInt();
                tracking.setResidFreqPPM(FloatToDouble(residFreqPPM));
                int skewPPM = dis.readInt();
                tracking.setSkewPPM(FloatToDouble(skewPPM));
                int rootDelay = dis.readInt();
                tracking.setRootDelay(FloatToDouble(rootDelay));
                int rootDispersion = dis.readInt();
                tracking.setRootDispersion(FloatToDouble(rootDispersion));
                int lastUpdateInterval = dis.readInt();
                tracking.setLastUpdateInterval(FloatToDouble(lastUpdateInterval));
                return tracking;
            }
        }
    }

    public ReplyHeader getReplyHeader() {
        return replyHeader;
    }

    public void setReplyHeader(ReplyHeader replyHeader) {
        this.replyHeader = replyHeader;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public int getStratum() {
        return stratum;
    }

    public void setStratum(int stratum) {
        this.stratum = stratum;
    }

    public int getLeapStatus() {
        return leapStatus;
    }

    public void setLeapStatus(int leapSeconds) {
        this.leapStatus = leapSeconds;
    }

    public double getCurrentCorrection() {
        return currentCorrection;
    }

    public void setCurrentCorrection(double currentCorrection) {
        this.currentCorrection = currentCorrection;
    }

    public double getLastOffset() {
        return lastOffset;
    }

    public void setLastOffset(double lastOffset) {
        this.lastOffset = lastOffset;
    }

    public double getRmsOffset() {
        return rmsOffset;
    }

    public void setRmsOffset(double rmsOffset) {
        this.rmsOffset = rmsOffset;
    }

    public double getFreqPPM() {
        return freqPPM;
    }

    public void setFreqPPM(double freqPPM) {
        this.freqPPM = freqPPM;
    }

    public double getResidFreqPPM() {
        return residFreqPPM;
    }

    public void setResidFreqPPM(double residFreqPPM) {
        this.residFreqPPM = residFreqPPM;
    }

    public double getSkewPPM() {
        return skewPPM;
    }

    public void setSkewPPM(double skewPPM) {
        this.skewPPM = skewPPM;
    }

    public double getRootDelay() {
        return rootDelay;
    }

    public void setRootDelay(double rootDelay) {
        this.rootDelay = rootDelay;
    }

    public double getRootDispersion() {
        return rootDispersion;
    }

    public void setRootDispersion(double rootDispersion) {
        this.rootDispersion = rootDispersion;
    }

    public double getLastUpdateInterval() {
        return lastUpdateInterval;
    }

    public void setLastUpdateInterval(double lastUpdateInterval) {
        this.lastUpdateInterval = lastUpdateInterval;
    }

    private static double FloatToDouble(int floatValue) {
        long exp, coef;
        long x;

        exp = (floatValue & 0xFFFFFFFFL) >> FLOAT_COEF_BITS;
        if (exp >= (1 << (FLOAT_EXP_BITS - 1))) {
            exp -= (1 << FLOAT_EXP_BITS);
        }
        exp -= FLOAT_COEF_BITS;

        coef = (floatValue % (1 << FLOAT_COEF_BITS) & 0x01FFFFFFL);
        if (coef >= (1 << (FLOAT_COEF_BITS - 1))) {
            coef -= (1 << FLOAT_COEF_BITS);
        }

        return coef * Math.pow(2.0, exp);
    }
}
