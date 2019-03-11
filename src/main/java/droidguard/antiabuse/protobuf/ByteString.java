package droidguard.antiabuse.protobuf;
import java.io.*;

public final class ByteString
{
    public static final ByteString a;
    private final byte[] b;
    private volatile int c;
    
    static {
        a = new ByteString(new byte[0]);
    }
    
    private ByteString(final byte[] b) {
        this.c = 0;
        this.b = b;
    }
    
    public static ByteString a(final String s) {
        try {
            return new ByteString(s.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported?");
        }
    }
    
    public static ByteString a(final byte[] array) {
        return a(array, 0, array.length);
    }
    
    public static ByteString a(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return new ByteString(array2);
    }
    
    public final int a() {
        return this.b.length;
    }
    
    public final byte[] b() {
        final int length = this.b.length;
        final byte[] array = new byte[length];
        System.arraycopy(this.b, 0, array, 0, length);
        return array;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ByteString)) {
                return false;
            }
            final ByteString fum = (ByteString)o;
            final int length = this.b.length;
            if (length != fum.b.length) {
                return false;
            }
            final byte[] b = this.b;
            final byte[] b2 = fum.b;
            for (int i = 0; i < length; ++i) {
                if (b[i] != b2[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int c;
        if ((c = this.c) == 0) {
            final byte[] b = this.b;
            int length;
            int i;
            int n;
            byte b2;
            for (length = this.b.length, i = 0, n = length; i < length; ++i, n = b2 + n * 31) {
                b2 = b[i];
            }
            if ((c = n) == 0) {
                c = 1;
            }
            this.c = c;
        }
        return c;
    }
}
