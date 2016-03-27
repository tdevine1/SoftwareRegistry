/* Class that avoids writing the serialization stream header to the file
 * for appending.
 */
package edu.fairmontstate.softwarefinder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SoftwareOutputStream extends ObjectOutputStream {

    public SoftwareOutputStream(FileOutputStream fis) throws IOException {
        super(fis);
    } // end constructor SoftwareOutputStream().
//================================================================================================================================
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    } // end method writeStreamHeader().
} // end class SoftwareOutputStream.
