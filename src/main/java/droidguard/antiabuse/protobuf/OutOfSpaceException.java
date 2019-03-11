package droidguard.antiabuse.protobuf;
import java.io.*;

public final class OutOfSpaceException extends IOException
{
    OutOfSpaceException() {
        super("CodedOutputStream was writing to build flat byte array and ran out of space.");
    }
}