package droidguard.antiabuse.request;
import java.io.IOException;
import java.util.*;

import droidguard.antiabuse.protobuf.CodedInputStream;
import droidguard.antiabuse.protobuf.CodedObject;
import droidguard.antiabuse.protobuf.CodedOutputStream;

public final class AntiabuseRequestData extends CodedObject
{
    private boolean a;
    private CommandAndPackageNameObject b;
    public List<CodedObject> c;
    private boolean d;
    private String e;
    private int f;
    
    public AntiabuseRequestData() {
        this.b = null;
        this.c = Collections.emptyList();
        this.e = "";
        this.f = -1;
    }
    
    public final AntiabuseRequestData a(final StringKeyValueCodedObject bba) {
        if (bba == null) {
            throw new NullPointerException();
        }
        if (this.c.isEmpty()) {
            this.c = new ArrayList<CodedObject>();
        }
        this.c.add(bba);
        return this;
    }
    
    public final AntiabuseRequestData a(final CommandAndPackageNameObject b) {
        if (b == null) {
            throw new NullPointerException();
        }
        this.a = true;
        this.b = b;
        return this;
    }
    
    public final AntiabuseRequestData a(final String e) {
        this.d = true;
        this.e = e;
        return this;
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
            	CommandAndPackageNameObject bbc1 = new CommandAndPackageNameObject();
                fun1.a(bbc1);
                a(bbc1);
                break;

            case 18: // '\022'
            	StringKeyValueCodedObject bba1 = new StringKeyValueCodedObject();
                fun1.a(bba1);
                a(bba1);
                break;

            case 26: // '\032'
                a(fun1.e());
                break;
            }
        } while(true);
    }
    
    @Override
    public final void a(final CodedOutputStream fuo) throws IOException {
        if (this.a) {
            fuo.a(1, this.b);
        }
        final Iterator<CodedObject> iterator = this.c.iterator();
        while (iterator.hasNext()) {
            fuo.a(2, iterator.next());
        }
        if (this.d) {
            fuo.a(3, this.e);
        }
    }
    
    @Override
    public final int b() {
        if (this.f < 0) {
            this.c();
        }
        return this.f;
    }
    
    @Override
    public final int c() {
        int n = 0;
        if (this.a) {
            n = CodedOutputStream.b(1, this.b) + 0;
        }
        final Iterator<CodedObject> iterator = this.c.iterator();
        while (iterator.hasNext()) {
            n += CodedOutputStream.b(2, iterator.next());
        }
        int f = n;
        if (this.d) {
            f = n + CodedOutputStream.b(3, this.e);
        }
        return this.f = f;
    }
    
    public boolean compareTo(AntiabuseRequestData another) {
    	if (this.a != another.a) {
    		System.out.println("a is different");
    		return false;
    	}
    	if (this.d != another.d) {
    		System.out.println("d is different");
    		return false;
    	}
    	if (this.f != another.f) {
    		System.out.println("f is different");
    		return false;
    	}
    	if (!this.e.equals(another.e)) {
    		System.out.println("e is different");
    		return false;
    	}
    	if (!this.b.compareTo(another.b)) {
    		System.out.println("b is different");
    		return false;
    	}
    	return true;
    }
}
