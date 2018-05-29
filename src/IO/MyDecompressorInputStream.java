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
            size += (b[i++]& 0xFF);
        i++;
        String filename = "bits";
        BitOutputStream bitOutputStream = new BitOutputStream(filename);
//        System.out.println("Reads:");
        for (int j = 0; j < size - 2; j++) {
//            System.out.println(b[i + j]);
            int value = b[i + j] & 0xFF;

            bitOutputStream.writeBits(8, value);
        }
        int last_value = (b[i + size - 2] & 0xFF) >> ((8 - (b[i + size - 1] & 0xFF)) & 0xFF);

        bitOutputStream.writeBits(b[i + size - 1] & 0xFF, last_value);
        bitOutputStream.close();
        BitInputStream bitInputStream = new BitInputStream(filename);
        HuffmanTree huffmanTree = new HuffmanTree(bitInputStream);
        //check if file is empty
        bitInputStream.close();
        BitOutputStream bitOutputStream2 = new BitOutputStream(filename + "2");
//        System.out.println("and then");
        int code_size = 0;
        for (i = i + size; i < len - 2; i++) {
//            System.out.println(b[i]);
            bitOutputStream2.writeBits(8, b[i] & 0xFF);
            code_size += 8;
        }
//        System.out.println((b[i] & 0xFF) >> ((8 - (b[i + 1] & 0xFF)) & 0xFF));
//        System.out.println(b[i + 1]);
        bitOutputStream2.writeBits(b[i + 1] == 0 ? 8 : b[i + 1], (b[i] & 0xFF) >> ((8 - (b[i + 1] == 0 ? 8 : b[i + 1]) & 0xFF)) & 0xFF);
        code_size += b[i + 1] == 0 ? 8 : b[i + 1];
        bitOutputStream2.close();
        BitInputStream bitInputStream2 = new BitInputStream(filename + "2");
        List<Byte> decoded = huffmanTree.getCoded_data(bitInputStream2, code_size);
        Byte last = decoded.get(decoded.size() - 1);
        decoded.remove(decoded.size() - 1);
        Byte before_last = decoded.get(decoded.size() - 1);
        decoded.remove(decoded.size() - 1);
        int index = 0;
        int discoverZero = 0;
        boolean check = false;
        for (Byte _byte : decoded) {
            if (discoverZero < 6) {
                b[index++] = _byte;
                if (!check) {
                    check = true;
                    continue;
                }
                if (check && _byte == 0) {
                    discoverZero++;
                    check = false;
                }
                continue;
            }
            String byteAsString = Integer.toBinaryString(_byte & 0xFF);
            while (byteAsString.length() != 8)
                byteAsString = '0' + byteAsString;
            char[] byteAsArray = byteAsString.toCharArray();
            for (int j = 0; j < 8; j++) {
                b[index++] = (byte) (byteAsArray[j] - 48);
            }

        }
        String byteAsString = Integer.toBinaryString(before_last & 0xFF);
        while (byteAsString.length() != 8)
            byteAsString = '0' + byteAsString;
        char[] byteAsArray = byteAsString.toCharArray();
        for (int j = 0; j < last; j++) {
            b[index++] = (byte) (byteAsArray[j] - 48);

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
