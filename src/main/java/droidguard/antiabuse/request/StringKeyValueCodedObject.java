package droidguard.antiabuse.request;

import java.io.IOException;

import droidguard.antiabuse.protobuf.CodedInputStream;
import droidguard.antiabuse.protobuf.CodedObject;
import droidguard.antiabuse.protobuf.CodedOutputStream;

public final class StringKeyValueCodedObject extends CodedObject
{
    private boolean commandWasSet;
    private String command;
    private boolean packageWasSet;
    private String packageName;
    private int e;
    
    public StringKeyValueCodedObject() {
        this.command = "";
        this.packageName = "";
        this.e = -1;
    }
    
    public final StringKeyValueCodedObject setKey(final String b) {
        this.commandWasSet = true;
        this.command = b;
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
            	setKey(fun1.e());
                break;

            case 18: // '\022'
            	setKey(fun1.e());
                break;
            }
        } while(true);
    }
    
    @Override
    public final void a(final CodedOutputStream fuo) throws IOException {
        if (this.commandWasSet) {
            fuo.a(1, this.command);
        }
        if (this.packageWasSet) {
            fuo.a(2, this.packageName);
        }
    }
    
    @Override
    public final int b() {
        if (this.e < 0) {
            this.c();
        }
        return this.e;
    }
    
    public final StringKeyValueCodedObject setValue(final String d) {
        this.packageWasSet = true;
        this.packageName = d;
        return this;
    }
    
    @Override
    public final int c() {
        int n = 0;
        if (this.commandWasSet) {
            n = CodedOutputStream.b(1, this.command) + 0;
        }
        int e = n;
        if (this.packageWasSet) {
            e = n + CodedOutputStream.b(2, this.packageName);
        }
        return this.e = e;
    }
    
    public boolean compareTo(StringKeyValueCodedObject another) {
    	if (this.commandWasSet != another.commandWasSet) {
    		System.out.println("a is different");
    		return false;
    	}
    	if (this.packageWasSet != another.packageWasSet) {
    		System.out.println("c is different");
    		return false;
    	}
    	if (this.e != another.e) {
    		System.out.println("e is different");
    		return false;
    	}
    	if (!this.command.equals(another.command)) {
    		System.out.println("b is different");
    		return false;
    	}
    	if (!this.packageName.equals(another.packageName)) {
    		System.out.println("d is different");
    		return false;
    	}
    	return true;
    }
}
