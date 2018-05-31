package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * This class response on compressing maze to byte array.
 * The compression method is using Huffman Tree.
 */
public class MyCompressorOutputStream extends OutputStream {
    /*The Stream we write to. */
    private OutputStream out;

    /**
     * c'tor
     * @param fileOutputStream - the stream we write into
     */
    public MyCompressorOutputStream(OutputStream fileOutputStream) {
        out = fileOutputStream;
    }

    /**
     *  Writes the specified byte to this output stream.
     * @param b - the byte
     * @throws IOException - if an I/O error occurs. In particular, an IOException may be thrown if theoutput stream
     * has been closed.
     */
    @Override
    public void write(int b) throws IOException { }


    /**
     * Writes b.length bytes from the specified byte array
     * to this output stream. The general contract for write(b)
     * is that it should have exactly the same effect as the call
     * write(b, 0, b.length).
     * @param b - the array with data.
     * @throws IOException -
     */
    public void write(byte[] b) throws IOException {
        int start_counter = 0;
        int current_index = 0;
        List<Byte> compressed = new ArrayList<>();

        // add the start, end and dimension of the maze. They separated with 0
        while (true) {
            compressed.add(b[current_index++]);//check
            if (b[current_index - 1] == 0) {
                start_counter++;
                if (start_counter == 6)
                    break;
                compressed.add(b[current_index++]);
            }
        }

        // represent 8 cell in the maze as one byte.
        byte[] comp_maze = new byte[(b.length - current_index + 7) / 8];
        int bit_index = 0;
        for (; current_index < b.length; current_index++, bit_index++) {
            comp_maze[bit_index / 8] |= (b[current_index] & 0xFF) << (7 - (bit_index % 8));//0xFF is for allow to byte be 0-255
        }

        //add the new representation
        for (byte _b : comp_maze) {
            compressed.add(_b);
        }
        compressed.add(bit_index%8==0?8:(byte)(bit_index%8));


        HuffmanTree huffmanTree = new HuffmanTree(compressed); // The tree will represent the compression
        List<Byte> answer = new LinkedList<>(); // contain the compression
        answer.addAll(huffmanTree.getCompressedTree());//Compress the representation of the tree.
        answer.addAll(huffmanTree.getCompressedData());//Compress the data if the maze.
        out.write(toPrimitives(answer.toArray(new Byte[answer.size()])));//write to the stream the data.
    }

    /**
     * Return every cell in oBytes as byte(premitive)
     * @param oBytes - the converted array
     * @return every cell in oBytes as byte(premitive)
     */
    private byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];
        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;

    }

}
