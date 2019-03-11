package droidguard.antiabuse.protobuf;

import java.io.IOException;
import java.io.OutputStream;

import droidguard.antiabuse.request.AntiabuseRequestData;

public class ProtocolBufferUtils {

	public static void writeTo(AntiabuseRequestData request, OutputStream outputStream) throws IOException {
		CodedOutputStream cos = CodedOutputStream.a(outputStream);
        request.a(cos);
        cos.a();
        outputStream.flush();
	}

}
