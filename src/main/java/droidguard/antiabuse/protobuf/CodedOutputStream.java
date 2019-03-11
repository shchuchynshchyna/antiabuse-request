package droidguard.antiabuse.protobuf;

import java.io.*;


public final class CodedOutputStream
{
    final int a;
    int b;
    final OutputStream c;
    private final byte[] d;
    
    private CodedOutputStream(final OutputStream c, final byte[] d) {
        this.c = c;
        this.d = d;
        this.b = 0;
        this.a = d.length;
    }
    
    CodedOutputStream(final byte[] d, final int b, final int n) {
        this.c = null;
        this.d = d;
        this.b = b;
        this.a = b + n;
    }
    
    public static int a(final int n) {
        if (n >= 0) {
            return d(n);
        }
        return 10;
    }
    
    public static int a(final long n) {
        if ((0xFFFFFFFFFFFFFF80L & n) == 0x0L) {
            return 1;
        }
        if ((0xFFFFFFFFFFFFC000L & n) == 0x0L) {
            return 2;
        }
        if ((0xFFFFFFFFFFE00000L & n) == 0x0L) {
            return 3;
        }
        if ((0xFFFFFFFFF0000000L & n) == 0x0L) {
            return 4;
        }
        if ((0xFFFFFFF800000000L & n) == 0x0L) {
            return 5;
        }
        if ((0xFFFFFC0000000000L & n) == 0x0L) {
            return 6;
        }
        if ((0xFFFE000000000000L & n) == 0x0L) {
            return 7;
        }
        if ((0xFF00000000000000L & n) == 0x0L) {
            return 8;
        }
        if ((Long.MIN_VALUE & n) == 0x0L) {
            return 9;
        }
        return 10;
    }
    
    public static int a(final String s) {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            return bytes.length + d(bytes.length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }
    
    public static CodedOutputStream a(final OutputStream outputStream) {
        return new CodedOutputStream(outputStream, new byte[4096]);
    }
    
    private void a(final byte[] array) throws IOException {
        final int length = array.length;
        if (this.a - this.b >= length) {
            System.arraycopy(array, 0, this.d, this.b, length);
            this.b += length;
            return;
        }
        final int n = this.a - this.b;
        System.arraycopy(array, 0, this.d, this.b, n);
        final int n2 = n + 0;
        final int b = length - n;
        this.b = this.a;
        this.b();
        if (b <= this.a) {
            System.arraycopy(array, n2, this.d, 0, b);
            this.b = b;
            return;
        }
        this.c.write(array, n2, b);
    }
    
    public static int b(final int n) {
        return d(IntegerPackerUtils.a(n, 0));
    }
    
    public static int b(final int n, final ByteString fum) {
        return b(n) + (d(fum.a()) + fum.a());
    }
    
    public static int b(int b, final CodedObject fur) {
        b = b(b);
        final int c = fur.c();
        return b + (c + d(c));
    }
    
    public static int b(final int n, final String s) {
        return b(n) + a(s);
    }
    
    private void b() throws IOException {
        if (this.c == null) {
            throw new OutOfSpaceException();
        }
        this.c.write(this.d, 0, this.b);
        this.b = 0;
    }
    
    private void b(long n) throws IOException {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.a((byte)(((int)n & 0x7F) | 0x80));
            n >>>= 7;
        }
        this.a((byte)n);
    }
    
    private void c(final long n) throws IOException {
        this.a((byte)((int)n & 0xFF));
        this.a((byte)((int)(n >> 8) & 0xFF));
        this.a((byte)((int)(n >> 16) & 0xFF));
        this.a((byte)((int)(n >> 24) & 0xFF));
        this.a((byte)((int)(n >> 32) & 0xFF));
        this.a((byte)((int)(n >> 40) & 0xFF));
        this.a((byte)((int)(n >> 48) & 0xFF));
        this.a((byte)((int)(n >> 56) & 0xFF));
    }
    
    public static int d(final int n) {
        if ((n & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((n & 0xFFFFC000) == 0x0) {
            return 2;
        }
        if ((0xFFE00000 & n) == 0x0) {
            return 3;
        }
        if ((0xF0000000 & n) == 0x0) {
            return 4;
        }
        return 5;
    }
    
    public static int d(final int n, final int n2) {
        return b(n) + a(n2);
    }
    
    public static int d(final int n, final long n2) {
        return b(n) + a(n2);
    }
    
    private static int e(final int n) {
        return n << 1 ^ n >> 31;
    }
    
    public static int e(final int n, final int n2) {
        return b(n) + d(n2);
    }
    
    public static int e(final int n, final long n2) {
        return b(n) + a(n2);
    }
    
    public static int f(final int n, final int n2) {
        return b(n) + d(e(n2));
    }
    
    private void g(final int n, final int n2) throws IOException {
        this.c(IntegerPackerUtils.a(n, n2));
    }
    
    public final void a() throws IOException {
        if (this.c != null) {
            this.b();
        }
    }
    
    public final void a(final byte b) throws IOException {
        if (this.b == this.a) {
            this.b();
        }
        this.d[this.b++] = b;
    }
    
    public final void a(final int n, final double n2) throws IOException {
        this.g(n, 1);
        this.c(Double.doubleToLongBits(n2));
    }
    
    public final void a(int floatToIntBits, final float n) throws IOException {
        this.g(floatToIntBits, 5);
        floatToIntBits = Float.floatToIntBits(n);
        this.a((byte)(floatToIntBits & 0xFF));
        this.a((byte)(floatToIntBits >> 8 & 0xFF));
        this.a((byte)(floatToIntBits >> 16 & 0xFF));
        this.a((byte)(floatToIntBits >> 24 & 0xFF));
    }
    
    public final void a(final int n, final int n2) throws IOException {
        this.g(n, 0);
        if (n2 >= 0) {
            this.c(n2);
            return;
        }
        this.b((long)n2);
    }
    
    public final void a(final int n, final long n2) throws IOException {
        this.g(n, 0);
        this.b(n2);
    }
    
    public final void a(final int n, final ByteString fum) throws IOException {
        this.g(n, 2);
        final byte[] b = fum.b();
        this.c(b.length);
        this.a(b);
    }
    
    public final void a(final int n, final CodedObject fur) throws IOException {
        this.g(n, 2);
        this.a(fur);
    }
    
    public final void a(final int n, final String s) throws IOException {
        this.g(n, 2);
        byte[] bytes = null;
		try {
			bytes = s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        this.c(bytes.length);
        try {
			this.a(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public final void a(int n, final boolean b) throws IOException {
        final int n2 = 0;
        this.g(n, 0);
        n = n2;
        if (b) {
            n = 1;
        }
        this.a((byte)n);
    }
    
    public final void a(final CodedObject fur) throws IOException {
        this.c(fur.b());
        fur.a(this);
    }
    
    public final void b(final int n, final int n2) throws IOException {
        this.g(n, 0);
        this.c(n2);
    }
    
    public final void b(final int n, final long n2) throws IOException {
        this.g(n, 0);
        this.b(n2);
    }
    
    public final void c(int n) throws IOException {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.a((byte)((n & 0x7F) | 0x80));
            n >>>= 7;
        }
        this.a((byte)n);
    }
    
    public final void c(final int n, final int n2) throws IOException {
        this.g(n, 0);
        this.c(e(n2));
    }
    
    public final void c(final int n, final long n2) throws IOException {
        this.g(n, 1);
        this.c(n2);
    }
}
