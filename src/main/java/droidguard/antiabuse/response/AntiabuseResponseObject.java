package droidguard.antiabuse.response;

import java.io.IOException;

import droidguard.antiabuse.protobuf.ByteString;
import droidguard.antiabuse.protobuf.CodedInputStream;
import droidguard.antiabuse.protobuf.CodedObject;
import droidguard.antiabuse.protobuf.CodedOutputStream;

public final class AntiabuseResponseObject extends CodedObject
{
    private boolean a;
    private ByteString b;
    private boolean c;
    private ByteString d;
    private int e;
    
    public AntiabuseResponseObject() {
        this.b = ByteString.a;
        this.d = ByteString.a;
        this.e = -1;
    }
    
    public static AntiabuseResponseObject a(final byte[] array) throws IOException {
        return (AntiabuseResponseObject)new AntiabuseResponseObject().b(array);
    }
    
    public final ByteString a() {
        return this.b;
    }
    
    @Override
    public final CodedObject a(CodedInputStream fun1) throws IOException
    {
        do
        {
            int i = fun1.a();
            switch(i)
            {
            default:
                if(fun1.b(i))
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
            	ByteString fum2 = fun1.f();
                c = true;
                d = fum2;
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
    }
    
    @Override
    public final int b() {
        if (this.e < 0) {
            this.c();
        }
        return this.e;
    }
    
    @Override
    public final int c() {
        int n = 0;
        if (this.a) {
            n = CodedOutputStream.b(1, this.b) + 0;
        }
        int e = n;
        if (this.c) {
            e = n + CodedOutputStream.b(2, this.d);
        }
        return this.e = e;
    }
    
    public final boolean d() {
        return this.a;
    }
    
    public final ByteString e() {
        return this.d;
    }
    
    public final boolean f() {
        return this.c;
    }
}
