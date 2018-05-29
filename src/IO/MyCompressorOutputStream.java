package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.*;

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

        int start_counter = 0;
        int current_index = 0;
        List<Byte> compressed = new ArrayList<>();

        while (true) {
            compressed.add(b[current_index++]);//check
            if (b[current_index - 1] == 0) {
                start_counter++;
                if (start_counter == 6)
                    break;
                compressed.add(b[current_index++]);
            }
        }

        byte[] comp_maze = new byte[(b.length - current_index + 7) / 8];
        int bit_index = 0;
        for (; current_index < b.length; current_index++, bit_index++) {
            comp_maze[bit_index / 8] |= (b[current_index] & 0xFF) << (7 - (bit_index % 8));
        }

        for (byte _b : comp_maze) {
            compressed.add(_b);
            //System.out.println(_b&0xFF);
        }
        compressed.add(bit_index%8==0?8:(byte)(bit_index%8));

        HuffmanTree huffmanTree = new HuffmanTree(compressed);
        List<Byte> answer = new LinkedList<>();
//        System.out.println("written:");
        answer.addAll(huffmanTree.getCompressedTree());
//        for (Byte aByte : huffmanTree.getCompressedTree()) {
//            System.out.println(aByte);
//        }
        answer.addAll(huffmanTree.getCompressedData());
//        System.out.println("and then");
//        for (Byte aByte : huffmanTree.getCompressedData()) {
//            System.out.println(aByte);
//        }

        out.write(toPrimitives(answer.toArray(new Byte[answer.size()])));
    }

    private byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];
        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;

    }

}
