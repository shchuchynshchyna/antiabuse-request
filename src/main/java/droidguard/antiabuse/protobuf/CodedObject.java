package droidguard.antiabuse.protobuf;
import java.io.*;

public abstract class CodedObject
{
    public final byte[] K() {
        final byte[] array = new byte[this.c()];
        this.a(array, 0, array.length);
        return array;
    }
    
    public abstract CodedObject a(final CodedInputStream p0) throws IOException;
    
    public abstract void a(final CodedOutputStream p0) throws IOException;
    
    public final void a(final byte[] array, final int n, final int n2) {
        try {
            final CodedOutputStream fuo = new CodedOutputStream(array, n, n2);
            this.a(fuo);
            if (fuo.c == null) {
                if (fuo.a - fuo.b != 0) {
                    throw new IllegalStateException("Did not write as much data as expected.");
                }
                return;
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to build byte array threw an IOException (should never happen).");
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to build flat array.");
    }
    
    public abstract int b();
    
    public final CodedObject b(final byte[] array) throws IOException {
        return this.b(array, 0, array.length);
    }
    
    public final CodedObject b(final byte[] array, final int n, final int n2) throws IOException {
        try {
            final CodedInputStream fun = new CodedInputStream(array, n, n2);
            this.a(fun);
            fun.a(0);
            return this;
        }
        catch (CodedInputStreamException fuq) {
            throw fuq;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from build byte array threw an IOException (should never happen).");
        }
    }
    
    public abstract int c();
}