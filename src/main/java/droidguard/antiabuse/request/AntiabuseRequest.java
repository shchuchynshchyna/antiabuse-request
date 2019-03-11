package droidguard.antiabuse.request;

public class AntiabuseRequest {
    private AntiabuseRequest() {
    }

    private static void addPairToRequest(AntiabuseRequestData request, String key, String value) {
        StringKeyValueCodedObject obj = new StringKeyValueCodedObject().setKey(key).setValue(value);
        request.a(obj);
    }

    public static AntiabuseRequestData createProtobufRequestData(String command)  {
        final AntiabuseRequestData antiabuseRequest = new AntiabuseRequestData();
        final CommandAndPackageNameObject commandAndPackageName = new CommandAndPackageNameObject();
        commandAndPackageName.a(command); // command
        commandAndPackageName.b("com.google.android.gms"); // package name

        antiabuseRequest.a(commandAndPackageName);
        antiabuseRequest.a("4.0.33 (910055-30)");

        addPairToRequest(antiabuseRequest, "BOARD", Build.BOARD);
        addPairToRequest(antiabuseRequest, "BOOTLOADER", Build.BOOTLOADER);
        addPairToRequest(antiabuseRequest, "BRAND", Build.BRAND);
        addPairToRequest(antiabuseRequest, "CPU_ABI", Build.CPU_ABI);
        addPairToRequest(antiabuseRequest, "CPU_ABI2", Build.CPU_ABI2);
        addPairToRequest(antiabuseRequest, "DEVICE", Build.DEVICE);
        addPairToRequest(antiabuseRequest, "DISPLAY", Build.DISPLAY);
        addPairToRequest(antiabuseRequest, "FINGERPRINT", Build.FINGERPRINT);
        addPairToRequest(antiabuseRequest, "HARDWARE", Build.HARDWARE);
        addPairToRequest(antiabuseRequest, "HOST", Build.HOST);
        addPairToRequest(antiabuseRequest, "ID", Build.ID);
        addPairToRequest(antiabuseRequest, "MANUFACTURER", Build.MANUFACTURER);
        addPairToRequest(antiabuseRequest, "MODEL", Build.MODEL);
        addPairToRequest(antiabuseRequest, "PRODUCT", Build.PRODUCT);
        addPairToRequest(antiabuseRequest, "RADIO", Build.RADIO);
        addPairToRequest(antiabuseRequest, "SERIAL", Build.SERIAL);
        addPairToRequest(antiabuseRequest, "TAGS", Build.TAGS);
        addPairToRequest(antiabuseRequest, "TIME", Long.toString(Build.TIME));
        addPairToRequest(antiabuseRequest, "TYPE", Build.TYPE);
        addPairToRequest(antiabuseRequest, "USER", Build.USER);
        addPairToRequest(antiabuseRequest, "VERSION.CODENAME", Build.VERSION.CODENAME);
        addPairToRequest(antiabuseRequest, "VERSION.INCREMENTAL", Build.VERSION.INCREMENTAL);
        addPairToRequest(antiabuseRequest, "VERSION.RELEASE", Build.VERSION.RELEASE);
        addPairToRequest(antiabuseRequest, "VERSION.SDK", Build.VERSION.SDK);
        addPairToRequest(antiabuseRequest, "VERSION.SDK_INT", Integer.toString(Build.VERSION.SDK_INT));

        return antiabuseRequest;
    }
}
