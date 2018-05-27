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
        int len = in.read(b);
        int i = 0, size = 0;
        while (b[i] != 0)
            size += b[i++];
        i++;
        String filename = "bits";
        BitOutputStream bitOutputStream = new BitOutputStream(filename);
        System.out.println("Reads:");
        for (int j = 0; j < size - 2; j++) {
            int value = b[i + j] & 0xFF;
            System.out.println(value);
            bitOutputStream.writeBits(8, value);
        }
        int last_value = (b[i + size - 2]&0xFF) >> ((8 - (b[i + size - 1]&0xFF))&0xFF);
        System.out.println(last_value);
        bitOutputStream.writeBits(b[i + size - 1]&0xFF, last_value);
        bitOutputStream.close();
        BitInputStream bitInputStream = new BitInputStream(filename);
        HuffmanTree huffmanTree = new HuffmanTree(bitInputStream);
        //check if file is empty
        bitInputStream.reset();
        for (i = i + size; i < len - 2; i++)
            bitOutputStream.writeBits(8, b[i]&0xFF);
        bitOutputStream.writeBits(b[i + 1], (b[i]&0xFF) >> ((8 - (b[i + 1]&0xFF))&0xFF));

        List<Byte> decoded = huffmanTree.getCoded_data(bitInputStream);

        int index=0;
        for (Byte _byte : decoded) {
            b[index++]=_byte;
        }
        return b.length;
        /*byte[] b_clone = b.clone();
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
*/
    }


}
