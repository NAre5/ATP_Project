package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.ArrayList;

public class WriterReader {

    public static void main(String[] args) {

        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = (new BreadthFirstSearch()).solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }
//        Position p1 = new Position( 30, 40);
//
//        Position p2 = new Position( 31, 41);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("mybean.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(solution);
            oos.close();

            // read object from file
            File file = new File("mybean.txt");
            FileInputStream fis = new FileInputStream(file.getPath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            Solution result = (Solution) ois.readObject();
            ois.close();
            ArrayList<AState> solutionPath1 = result.getSolutionPath();
            for (int i = 0; i < solutionPath1.size(); i++) {
                System.out.println(String.format("%s. %s", i, solutionPath1.get(i)));
            }
            //System.out.println(result.toString());
            //System.out.println(p2.toString());



        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
