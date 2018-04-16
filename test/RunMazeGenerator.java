package test;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.util.Random;

public class RunMazeGenerator {
    public static void main(String[] args) {
        SimpleMazeGenerator smg = new SimpleMazeGenerator();
        Maze m = smg.generate(5,5);
        for (int i = 0; i <5 ; i++) {
            for (int j = 0; j <5 ; j++) {
                System.out.print(m.getMaze()[i][j]+" ");

            }
            System.out.println();
        }

    }
}
