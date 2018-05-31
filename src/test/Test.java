package test;

import IO.BitInputStream;
import IO.BitOutputStream;
import IO.HuffmanTree;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import javax.print.attribute.standard.QueuedJobCount;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.nio.file.Files;

public class Test {
    public static void main(String[] args) {

        byte[] bytes = new byte[]{55,4,3,8,45,9};
        System.out.println(Arrays.toString(bytes));
        String arr = Arrays.toString(bytes);


    }

    //public static void kich()

    public static byte[] compress(byte[] input) {
        // Compressor with highest level of compression
        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);
        // Give the compressor the data to compress
        compressor.setInput(input);
        compressor.finish();
        // Create an expandable byte array to hold the compressed data.
        // It is not necessary that the compressed data will be smaller than
        // the uncompressed data.
        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
        // Compress the data
        byte[] buf = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException e) {
        }

// Get the compressed data
        byte[] compressedData = bos.toByteArray();
        return compressedData;
    }

    public static byte[] decompress(byte[] compressedData) {
        // Create the decompressor and give it the data to compress
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedData);

// Create an expandable byte array to hold the decompressed data
        ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedData.length);
// Decompress the data
        byte[] buf = new byte[1024];
        while (!decompressor.finished()) {
            try {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            } catch (DataFormatException e) {
            }
        }
        try {
            bos.close();
        } catch (IOException e) {
        }

// Get the decompressed data
        byte[] decompressedData = bos.toByteArray();
        return decompressedData;
    }


}
