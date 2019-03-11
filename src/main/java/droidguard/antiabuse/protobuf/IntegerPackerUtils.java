package droidguard.antiabuse.protobuf;
public final class IntegerPackerUtils
{
    static final int a;
    static final int b;
    static final int c;
    static final int d;
    
    static {
        a = 11;
        b = 12;
        c = 16;
        d = 26;
    }
    
    static int a(final int n) {
        return n & 0x7;
    }
    
    static int a(final int n, final int n2) {
        return n << 3 | n2;
    }
    
    public static int b(final int n) {
        return n >>> 3;
    }
}