package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream{
    private InputStream in;
    public MyDecompressorInputStream(InputStream fileInputStream) {
        in = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
