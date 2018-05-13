package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream fileOutputStream) {
        out = fileOutputStream;
    }

    @Override
    public void write(int b) throws IOException {
        //send exception

    }

    public void write(byte[] b) throws IOException {
        int last_bit = 0;
        byte counter = 0;

        int start_counter=0;
        int current_index=0;
        List<Byte> compressed = new ArrayList<>();

        while (true) {
            compressed.add(b[current_index++]);//check
            if (b[current_index-1] == 0) {
                start_counter++;
                if (start_counter==6)
                    break;
                compressed.add(b[current_index++]);
            }
        }


        for (;current_index < b.length; current_index++) {
            if (b[current_index] == last_bit) {
                if (counter == 127) {
                    compressed.add(counter);
                    compressed.add((byte) 0);
                    counter = 0;
                }
                counter++;//add if counter too big
            } else {
                compressed.add(counter);
                last_bit = (last_bit + 1) % 2;
                counter = 1;
            }
        }
        compressed.add(counter);

        out.write(toPrimitives(compressed.toArray(new Byte[compressed.size()])));
    }

    private byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];
        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;

    }

}
