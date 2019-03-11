package droidguard.antiabuse.protobuf;
import java.io.*;

public final class CodedInputStreamException extends IOException
{
    private CodedInputStreamException(final String s) {
        super(s);
    }
    
    static CodedInputStreamException a() {
        return new CodedInputStreamException("While parsing build protocol message, the input ended unexpectedly in the middle of build field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }
    
    static CodedInputStreamException b() {
        return new CodedInputStreamException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
    
    static CodedInputStreamException c() {
        return new CodedInputStreamException("CodedInputStream encountered build malformed varint.");
    }
    
    static CodedInputStreamException d() {
        return new CodedInputStreamException("Protocol message contained an invalid tag (zero).");
    }
    
    static CodedInputStreamException e() {
        return new CodedInputStreamException("Protocol message end-group tag did not match expected tag.");
    }
    
    static CodedInputStreamException f() {
        return new CodedInputStreamException("Protocol message tag had invalid wire type.");
    }
    
    static CodedInputStreamException g() {
        return new CodedInputStreamException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
    
    static CodedInputStreamException h() {
        return new CodedInputStreamException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }
}
