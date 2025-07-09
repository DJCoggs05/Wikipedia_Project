package edu.bsu.cs;

import edu.bsu.cs.Exceptions.openInputStreamException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class RevisionInputStream {
    protected byte[] inputStream;
    public RevisionInputStream(InputStream inputStream) throws openInputStreamException {
        try{
            this.inputStream = inputStream.readAllBytes();
        }catch (Exception e) {
            throw new openInputStreamException();
        }
    }

    public ByteArrayInputStream openInputStream() {
        return new ByteArrayInputStream(inputStream);
    }
}
