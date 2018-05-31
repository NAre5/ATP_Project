package test;

import IO.BitInputStream;
import IO.BitOutputStream;
import IO.HuffmanTree;
import algorithms.mazeGenerators.*;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import javax.print.attribute.standard.QueuedJobCount;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        System.out.println(MyMazeGenerator.class.getName());



        String className = "algorithms.mazeGenerators.MyMazeGenerator";
        try {
            IMazeGenerator generator = (IMazeGenerator) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        String name = "algorithms.search.DepthFirstSearch";
//        try {
//            Class cls = Class.forName(name);
//            Constructor constructor = cls.getConstructor(cls);
//            ISearchingAlgorithm o = (ISearchingAlgorithm) constructor.newInstance();
//            System.out.println(o.getClass());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        byte[] bytes = new byte[]{55,4,3,8,45,9};
//        System.out.println(Arrays.toString(bytes));
//        String arr = Arrays.toString(bytes);


    }

}

//class A
//{
//    A(){
//        System.out.println("A");
//    }
//}
//
//class B
//{
//    B(){
//        System.out.println("B");
//    }
//}