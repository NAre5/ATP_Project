package test;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.util.ArrayList;
import java.util.Random;

public class RunMazeGenerator {
    public static void main(String[] args) {
        MyMazeGenerator smg = new MyMazeGenerator();

        Maze m = smg.generate(7,15);
        /*for (int i = 0; i <m.getMaze().length ; i++) {
            for (int j = 0; j <m.getMaze()[i].length ; j++) {
                System.out.print(m.getMaze()[i][j]+" ");

            }
            System.out.println();
        }*/
        m.Print();

    }
}
