package test;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.util.Random;

public class RunMazeGenerator {
    public static void main(String[] args) {
        SimpleMazeGenerator smg = new SimpleMazeGenerator();
        Maze m = smg.generate(5,5);
        /*for (int i = 0; i <m.getMaze().length ; i++) {
            for (int j = 0; j <m.getMaze()[i].length ; j++) {
                System.out.print(m.getMaze()[i][j]+" ");

            }
            System.out.println();
        }*/
        m.Print();
        Position position = new Position(1,2);
        System.out.println(position);

    }
}
