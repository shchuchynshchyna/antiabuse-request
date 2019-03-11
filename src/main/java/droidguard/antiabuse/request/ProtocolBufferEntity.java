package droidguard.antiabuse.request;

import droidguard.antiabuse.protobuf.CodedOutputStream;
import droidguard.antiabuse.request.AntiabuseRequestData;
import org.apache.http.entity.AbstractHttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProtocolBufferEntity extends AbstractHttpEntity
	{
	    private final AntiabuseRequestData antiabuseRequest;
	    
	    public ProtocolBufferEntity(final AntiabuseRequestData a) {
	        this.antiabuseRequest = a;
	        this.setContentType("application/x-protobuf");
	    }
	    
	    public final InputStream getContent() {
	        throw new UnsupportedOperationException("getContent() not supported.");
	    }
	    
	    public final long getContentLength() {
	        return this.antiabuseRequest.c();
	    }
	    
	    public final boolean isRepeatable() {
	        return true;
	    }
	    
	    public final boolean isStreaming() {
	        return false;
	    }
	    
	    public final void writeTo(final OutputStream outputStream) throws IOException {
	        final CodedOutputStream cos = CodedOutputStream.a(outputStream);
	        this.antiabuseRequest.a(cos);
	        cos.a();
	        outputStream.flush();
	    }
	}