package IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class response aboute read the compress maze from the input stream and decompress him.
 */
public class MyDecompressorInputStream extends InputStream {
    //The stream we read from
    private InputStream in;

    /**
     * c'tor
     * @param fileInputStream - The stream we read from
     */
    public MyDecompressorInputStream(InputStream fileInputStream) {
        in = fileInputStream;
    }

    /**
     * Reads the next byte of data from the input stream.
     * @return  the next byte of data, or -1 if the end of the
     *             stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * Reads some number of bytes from the input stream and stores them into
     * the buffer array b. The number of bytes actually read is
     * returned as an integer.  This method blocks until input data is
     * available, end of file is detected, or an exception is thrown.
     * @param b - the buffer into which the data is read
     * @return the total number of bytes read into the buffer, or
     *             -1 if there is no more data because the end of
     *             the stream has been reached.
     * @throws IOException -
     */
    @Override
    public int read(byte[] b) throws IOException {
        int len = in.read(b);
        int i = 0, size = 0;
        while (b[i] != 0)
            size += (b[i++]& 0xFF);
        i++;
        String filename = "bits";
        BitOutputStream bitOutputStream = new BitOutputStream(filename);
        for (int j = 0; j < size - 2; j++) {
            int value = b[i + j] & 0xFF;

            bitOutputStream.writeBits(8, value);
        }
        int last_value = (b[i + size - 2] & 0xFF) >> ((8 - (b[i + size - 1] & 0xFF)) & 0xFF);

        bitOutputStream.writeBits(b[i + size - 1] & 0xFF, last_value);
        bitOutputStream.close();
        BitInputStream bitInputStream = new BitInputStream(filename);
        HuffmanTree huffmanTree = new HuffmanTree(bitInputStream);
        bitInputStream.close();
        BitOutputStream bitOutputStream2 = new BitOutputStream(filename);
        bitOutputStream2.flush();
//        BitOutputStream bitOutputStream2 = new BitOutputStream(filename + "2");
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
        BitInputStream bitInputStream2 = new BitInputStream(filename);
        List<Byte> decoded = huffmanTree.getCoded_data(bitInputStream2, code_size);
        bitInputStream2.close();
        Files.deleteIfExists(Paths.get(filename));
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
            StringBuilder byteAsString = new StringBuilder(Integer.toBinaryString(_byte & 0xFF));
            while (byteAsString.length() != 8)
                byteAsString.insert(0, '0');
            char[] byteAsArray = byteAsString.toString().toCharArray();
            for (int j = 0; j < 8; j++) {
                b[index++] = (byte) (byteAsArray[j] - 48);
            }

        }
        StringBuilder byteAsString = new StringBuilder(Integer.toBinaryString(before_last & 0xFF));
        while (byteAsString.length() != 8)
            byteAsString.insert(0, '0');
        char[] byteAsArray = byteAsString.toString().toCharArray();
        for (int j = 0; j < last; j++) {
            b[index++] = (byte) (byteAsArray[j] - 48);

        }
        return b.length;
    }
}
