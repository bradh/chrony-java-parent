package net.frogmouth.chronyjava;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/** Chrony Tracking results. */
public class Tracking {
    private ReplyHeader replyHeader;
    private int refId;
    private int stratum;
    private int leapStatus;
    private TimeSpec timeSpec;
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
                tracking.setTimeSpec(TimeSpec.fromBytes(dis.readNBytes(12)));
                int currentCorrection = dis.readInt();
                tracking.setCurrentCorrection(
                        ParseUtils.ChronyFloatToJavaDouble(currentCorrection));
                int lastOffset = dis.readInt();
                tracking.setLastOffset(ParseUtils.ChronyFloatToJavaDouble(lastOffset));
                int rmsOffset = dis.readInt();
                tracking.setRmsOffset(ParseUtils.ChronyFloatToJavaDouble(rmsOffset));
                int freqPPM = dis.readInt();
                tracking.setFreqPPM(ParseUtils.ChronyFloatToJavaDouble(freqPPM));
                int residFreqPPM = dis.readInt();
                tracking.setResidFreqPPM(ParseUtils.ChronyFloatToJavaDouble(residFreqPPM));
                int skewPPM = dis.readInt();
                tracking.setSkewPPM(ParseUtils.ChronyFloatToJavaDouble(skewPPM));
                int rootDelay = dis.readInt();
                tracking.setRootDelay(ParseUtils.ChronyFloatToJavaDouble(rootDelay));
                int rootDispersion = dis.readInt();
                tracking.setRootDispersion(ParseUtils.ChronyFloatToJavaDouble(rootDispersion));
                int lastUpdateInterval = dis.readInt();
                tracking.setLastUpdateInterval(
                        ParseUtils.ChronyFloatToJavaDouble(lastUpdateInterval));
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

    public TimeSpec getTimeSpec() {
        return timeSpec;
    }

    public void setTimeSpec(TimeSpec timeSpec) {
        this.timeSpec = timeSpec;
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
}
