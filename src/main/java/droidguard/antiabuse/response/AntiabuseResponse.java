package droidguard.antiabuse.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AntiabuseResponse {
    private final static Logger logger = LoggerFactory.getLogger(AntiabuseResponse.class);
    private String apkUrl;
    private byte[] vmCode;
    private String vmChecksum;

    public static AntiabuseResponse parseResponse(byte[] responseData) throws IOException {
        logger.info("Read bytes from file: " + responseData.length);

        AntiabuseResponseObject responseObject = AntiabuseResponseObject.a(responseData);
        byte[] program = responseObject.a().b();
        byte[] signature = responseObject.e().b();
        if (!signatureVerifier(program, signature)) {
            logger.error("Response data: Program signature verification failed.");
        }
        if (!responseObject.d()) {
            logger.error("Response data: program issue"); // byteCode = program
        }
        if (!responseObject.f()) {
            logger.error("Response data: signature issue");
        }

        byte[] payload = responseObject.a().b();
        logger.info("Payload size: " + payload.length);
        AntiabusePayloadObject payloadObject = AntiabusePayloadObject.a(payload);
        if (!payloadObject.d()) {
            logger.error("Payload data: byteCode issue");
        }
        if (!payloadObject.f()) {
            logger.error("Payload data: vmUrl issue");
        }
        if (!payloadObject.h()) {
            logger.error("Payload data: vmChecksum issue");
        }
        if (!payloadObject.j()) {
            logger.error("Payload data: expiryTimeSecs issue");
        }

        byte[] byteCode = payloadObject.a().b();
        logger.info("byteCode size: " + byteCode.length);

        String vmChecksum = processString(payloadObject.g().b(), false);
        logger.info("vmChecksum: " + vmChecksum);

        String theAPKUrl = payloadObject.e();
        logger.info("vmUrl: " + theAPKUrl);

        long expiryTimeSecs = payloadObject.i();
        logger.info("expiryTimeSecs: " + expiryTimeSecs);

        AntiabuseResponse response = new AntiabuseResponse();
        response.apkUrl = theAPKUrl;
        response.vmCode = byteCode;
        response.vmChecksum = vmChecksum;
        return response;
    }

    public String getVmChecksum() {
        return vmChecksum;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public byte[] getVmCode() {
        return vmCode;
    }

    static String processString(final byte[] array, final boolean b) {
        final char[] d = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        final int length = array.length;
        final StringBuilder sb = new StringBuilder(length * 2);
        for (int n = 0; n < length && (!b || n != length - 1 || (array[n] & 0xFF) != 0x0); ++n) {
            sb.append(d[(array[n] & 0xF0) >>> 4]);
            sb.append(d[array[n] & 0xF]);
        }
        return sb.toString();
    }

    static boolean signatureVerifier(byte[] program, byte[] signature) {
        logger.info("program size = " + program.length);
        logger.info("signature size = " + signature.length);
        return true;
    }
}
