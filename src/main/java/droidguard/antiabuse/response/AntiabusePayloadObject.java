package droidguard.antiabuse.response;

import java.io.IOException;

import droidguard.antiabuse.protobuf.ByteString;
import droidguard.antiabuse.protobuf.CodedInputStream;
import droidguard.antiabuse.protobuf.CodedObject;
import droidguard.antiabuse.protobuf.CodedOutputStream;

public final class AntiabusePayloadObject extends CodedObject
{
    private boolean a;
    private ByteString b;
    private boolean c;
    private String d;
    private boolean e;
    private ByteString f;
    private boolean g;
    private int h;
    private int i;
    
    public AntiabusePayloadObject() {
        this.b = ByteString.a;
        this.d = "";
        this.f = ByteString.a;
        this.h = 0;
        this.i = -1;
    }
    
    public static AntiabusePayloadObject a(final byte[] array) throws IOException {
        return (AntiabusePayloadObject)new AntiabusePayloadObject().b(array);
    }
    
    public final ByteString a() {
        return this.b;
    }
    
    public final CodedObject a(CodedInputStream fun1) throws IOException
    {
        do
        {
            int k = fun1.a();
            switch(k)
            {
            default:
                if(fun1.b(k))
                    continue;
                // fall through

            case 0: // '\0'
                return this;

            case 10: // '\n'
                ByteString fum1 = fun1.f();
                a = true;
                b = fum1;
                break;

            case 18: // '\022'
                String s = fun1.e();
                c = true;
                d = s;
                break;

            case 26: // '\032'
            	ByteString fum2 = fun1.f();
                e = true;
                f = fum2;
                break;

            case 32: // ' '
                int l = fun1.h();
                g = true;
                h = l;
                break;
            }
        } while(true);
    }
    
    @Override
    public final void a(final CodedOutputStream fuo) throws IOException {
        if (this.a) {
            fuo.a(1, this.b);
        }
        if (this.c) {
            fuo.a(2, this.d);
        }
        if (this.e) {
            fuo.a(3, this.f);
        }
        if (this.g) {
            fuo.b(4, this.h);
        }
    }
    
    @Override
    public final int b() {
        if (this.i < 0) {
            this.c();
        }
        return this.i;
    }
    
    @Override
    public final int c() {
        int n = 0;
        if (this.a) {
            n = CodedOutputStream.b(1, this.b) + 0;
        }
        int n2 = n;
        if (this.c) {
            n2 = n + CodedOutputStream.b(2, this.d);
        }
        int n3 = n2;
        if (this.e) {
            n3 = n2 + CodedOutputStream.b(3, this.f);
        }
        int i = n3;
        if (this.g) {
            i = n3 + CodedOutputStream.e(4, this.h);
        }
        return this.i = i;
    }
    
    public final boolean d() {
        return this.a;
    }
    
    public final String e() {
        return this.d;
    }
    
    public final boolean f() {
        return this.c;
    }
    
    public final ByteString g() {
        return this.f;
    }
    
    public final boolean h() {
        return this.e;
    }
    
    public final int i() {
        return this.h;
    }
    
    public final boolean j() {
        return this.g;
    }
}
