package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream fileInputStream) {
        in = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] b_clone = b.clone();
        int ans = in.read(b_clone);
        //System.out.println(Arrays.toString(b));
        int start_counter = 0;
        int i = 0;

        while (true) {
            b[i] = b_clone[i];
            i++;
            if (b_clone[i - 1] == 0) {
                start_counter++;
                if (start_counter == 6)
                    break;
                b[i] = b_clone[i];
                i++;
            }
        }
        //System.out.println(Arrays.toString(b));
        int j = i;
        byte bit = 0;
        for (; i < b_clone.length; i++) {
            for (int k = 0; k < b_clone[i]; k++) {
                b[j+k] = bit;
            }
            j+=b_clone[i];
            if (bit==1)
                bit=0;
            else
                bit=1;
        }

        return ans;
    }


}
