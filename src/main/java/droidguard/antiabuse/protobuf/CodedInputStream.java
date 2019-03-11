package droidguard.antiabuse.protobuf;
import java.io.*;
import java.util.*;


public final class CodedInputStream
{
    private final byte[] a;
    private int b;
    private int c;
    private int d;
    private final InputStream e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    
    public CodedInputStream(final InputStream e) {
        this.h = Integer.MAX_VALUE;
        this.j = 64;
        this.k = 67108864;
        this.a = new byte[4096];
        this.b = 0;
        this.d = 0;
        this.e = e;
    }
    
    CodedInputStream(final byte[] a, final int d, final int n) {
        this.h = Integer.MAX_VALUE;
        this.j = 64;
        this.k = 67108864;
        this.a = a;
        this.b = d + n;
        this.d = d;
        this.e = null;
    }
    
    private boolean a(final boolean b) throws IOException {
        if (this.d < this.b) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
        if (this.g + this.b == this.h) {
            if (b) {
                throw CodedInputStreamException.a();
            }
            return false;
        }
        else {
            this.g += this.b;
            this.d = 0;
            int read;
            if (this.e == null) {
                read = -1;
            }
            else {
                read = this.e.read(this.a);
            }
            this.b = read;
            if (this.b == 0 || this.b < -1) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.b + "\nThe InputStream implementation is buggy.");
            }
            if (this.b == -1) {
                this.b = 0;
                if (b) {
                    throw CodedInputStreamException.a();
                }
                return false;
            }
            else {
                this.m();
                final int n = this.g + this.b + this.c;
                if (n > this.k || n < 0) {
                    throw CodedInputStreamException.h();
                }
                return true;
            }
        }
    }
    
    private void d(final int n) throws IOException {
        if (n < 0) {
            throw CodedInputStreamException.b();
        }
        if (this.g + this.d + n > this.h) {
            this.d(this.h - this.g - this.d);
            throw CodedInputStreamException.a();
        }
        if (n <= this.b - this.d) {
            this.d += n;
        }
        else {
            final int b = this.b;
            final int d = this.d;
            this.g += this.b;
            this.d = 0;
            this.b = 0;
            int n2;
            for (int i = b - d; i < n; i += n2, this.g += n2) {
                if (this.e == null) {
                    n2 = -1;
                }
                else {
                    n2 = (int)this.e.skip(n - i);
                }
                if (n2 <= 0) {
                    throw CodedInputStreamException.a();
                }
            }
        }
    }
    
    private void m() {
        this.b += this.c;
        final int n = this.g + this.b;
        if (n > this.h) {
            this.c = n - this.h;
            this.b -= this.c;
            return;
        }
        this.c = 0;
    }
    
    public final int a() throws IOException {
        int n;
        if (this.d == this.b && !this.a(false)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return this.f = 0;
        }
        this.f = this.h();
        if (this.f == 0) {
            throw CodedInputStreamException.d();
        }
        return this.f;
    }
    
    public final void a(final int n) throws IOException {
        if (this.f != n) {
            throw CodedInputStreamException.e();
        }
    }
    
    public final void a(final CodedObject fur) throws IOException {
        final int h = this.h();
        if (this.i >= this.j) {
            throw CodedInputStreamException.g();
        }
        if (h < 0) {
            throw CodedInputStreamException.b();
        }
        final int h2 = h + (this.g + this.d);
        final int h3 = this.h;
        if (h2 > h3) {
            throw CodedInputStreamException.a();
        }
        this.h = h2;
        this.m();
        ++this.i;
        fur.a(this);
        this.a(0);
        --this.i;
        this.h = h3;
        this.m();
    }
    
    public final long b() throws IOException {
        return this.i();
    }
    
    public final boolean b(final int n) throws IOException {
        switch (IntegerPackerUtils.a(n)) {
            default: {
                throw CodedInputStreamException.f();
            }
            case 0: {
                this.h();
                return true;
            }
            case 1: {
                this.k();
                return true;
            }
            case 2: {
                this.d(this.h());
                return true;
            }
            case 3: {
                int a;
                do {
                    a = this.a();
                } while (a != 0 && this.b(a));
                this.a(IntegerPackerUtils.a(IntegerPackerUtils.b(n), 4));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.j();
                return true;
            }
        }
    }
    
    public final int c() throws IOException {
        return this.h();
    }
    
    public final byte[] c(int i) throws IOException {
        if (i < 0) {
            throw CodedInputStreamException.b();
        }
        if (this.g + this.d + i > this.h) {
            this.d(this.h - this.g - this.d);
            throw CodedInputStreamException.a();
        }
        if (i <= this.b - this.d) {
            final byte[] array = new byte[i];
            System.arraycopy(this.a, this.d, array, 0, i);
            this.d += i;
            return array;
        }
        if (i < 4096) {
            final byte[] array2 = new byte[i];
            int n = this.b - this.d;
            System.arraycopy(this.a, this.d, array2, 0, n);
            this.d = this.b;
            this.a(true);
            while (i - n > this.b) {
                System.arraycopy(this.a, 0, array2, n, this.b);
                n += this.b;
                this.d = this.b;
                this.a(true);
            }
            System.arraycopy(this.a, 0, array2, n, i - n);
            this.d = i - n;
            return array2;
        }
        final int d = this.d;
        final int b = this.b;
        this.g += this.b;
        this.d = 0;
        this.b = 0;
        final Vector<byte[]> vector = new Vector<byte[]>();
        int length;
        for (int j = i - (b - d); j > 0; j -= length) {
            final byte[] array3 = new byte[Math.min(j, 4096)];
            int read;
            for (int k = 0; k < array3.length; k += read) {
                if (this.e == null) {
                    read = -1;
                }
                else {
                    read = this.e.read(array3, k, array3.length - k);
                }
                if (read == -1) {
                    throw CodedInputStreamException.a();
                }
                this.g += read;
            }
            length = array3.length;
            vector.addElement(array3);
        }
        final byte[] array4 = new byte[i];
        int n2 = b - d;
        System.arraycopy(this.a, d, array4, 0, n2);
        byte[] array5;
        for (i = 0; i < vector.size(); ++i) {
            array5 = vector.elementAt(i);
            System.arraycopy(array5, 0, array4, n2, array5.length);
            n2 += array5.length;
        }
        return array4;
    }
    
    public final boolean d() throws IOException {
        return this.h() != 0;
    }
    
    public final String e() throws IOException {
        final int h = this.h();
        if (h <= this.b - this.d && h > 0) {
            final String s = new String(this.a, this.d, h, "UTF-8");
            this.d += h;
            return s;
        }
        return new String(this.c(h), "UTF-8");
    }
    
    public final ByteString f() throws IOException {
        final int h = this.h();
        if (h <= this.b - this.d && h > 0) {
            final ByteString a = ByteString.a(this.a, this.d, h);
            this.d += h;
            return a;
        }
        return ByteString.a(this.c(h));
    }
    
    public final int g() throws IOException {
        final int h = this.h();
        return -(h & 0x1) ^ h >>> 1;
    }
    
    public final int h() throws IOException {
        int l = this.l();
        if (l < 0) {
            final int n = l & 0x7F;
            final byte i = this.l();
            if (i >= 0) {
                return n | i << 7;
            }
            final int n2 = n | (i & 0x7F) << 7;
            final byte j = this.l();
            if (j >= 0) {
                return n2 | j << 14;
            }
            final int n3 = n2 | (j & 0x7F) << 14;
            final byte k = this.l();
            if (k >= 0) {
                return n3 | k << 21;
            }
            final byte m = this.l();
            final int n4 = l = (n3 | (k & 0x7F) << 21 | m << 28);
            if (m < 0) {
                for (int n5 = 0; n5 < 5; ++n5) {
                    l = n4;
                    if (this.l() >= 0) {
                        return l;
                    }
                }
                throw CodedInputStreamException.c();
            }
        }
        return l;
    }
    
    public final long i() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte l = this.l();
            n |= (l & 0x7F) << i;
            if ((l & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw CodedInputStreamException.c();
    }
    
    public final int j() throws IOException {
        return (this.l() & 0xFF) | (this.l() & 0xFF) << 8 | (this.l() & 0xFF) << 16 | (this.l() & 0xFF) << 24;
    }
    
    public final long k() throws IOException {
        return (this.l() & 0xFFL) << 8 | (this.l() & 0xFFL) | (this.l() & 0xFFL) << 16 | (this.l() & 0xFFL) << 24 | (this.l() & 0xFFL) << 32 | (this.l() & 0xFFL) << 40 | (this.l() & 0xFFL) << 48 | (this.l() & 0xFFL) << 56;
    }
    
    public final byte l() throws IOException {
        if (this.d == this.b) {
            this.a(true);
        }
        return this.a[this.d++];
    }
}
