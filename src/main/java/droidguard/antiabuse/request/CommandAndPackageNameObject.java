package droidguard.antiabuse.request;

import java.io.IOException;

import droidguard.antiabuse.protobuf.CodedInputStream;
import droidguard.antiabuse.protobuf.CodedObject;
import droidguard.antiabuse.protobuf.CodedOutputStream;

public final class CommandAndPackageNameObject extends CodedObject
{
    private boolean a;
    private String b;
    private boolean c;
    private String d;
    private int e;
    
    public CommandAndPackageNameObject() {
        this.b = "";
        this.d = "";
        this.e = -1;
    }
    
    public final CommandAndPackageNameObject a(final String b) {
        this.a = true;
        this.b = b;
        return this;
    }
    
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
                a(fun1.e());
                break;

            case 18: // '\022'
                b(fun1.e());
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
    
    public final CommandAndPackageNameObject b(final String d) {
        this.c = true;
        this.d = d;
        return this;
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
    
    public boolean compareTo(CommandAndPackageNameObject another) {
    	if (this.a != another.a) {
    		System.out.println("a is different");
    		return false;
    	}
    	if (this.c != another.c) {
    		System.out.println("c is different");
    		return false;
    	}
    	if (this.e != another.e) {
    		System.out.println("e is different");
    		return false;
    	}
    	if (!this.b.equals(another.b)) {
    		System.out.println("b is different");
    		return false;
    	}
    	if (!this.d.equals(another.d)) {
    		System.out.println("d is different");
    		return false;
    	}
    	return true;
    }
}
