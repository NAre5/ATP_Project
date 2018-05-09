package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream{
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream fileOutputStream) {
        out = fileOutputStream;
    }

    @Override
    public void write(int b) throws IOException {
        //turn b into bytes
        //send bytes to

    }

    public void write(byte[] b) throws IOException {
        ///odujhdij
    }
}
