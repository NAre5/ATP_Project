package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WriterReader {

    public static void main(String[] args) {

        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = (new BreadthFirstSearch()).solve(searchableMaze);

        Position p1 = new Position( 30, 40);

        Position p2 = new Position( 31, 41);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("mybean.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(p1);
            oos.close();

            // read object from file
            FileInputStream fis = new FileInputStream("mybean.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Position result = (Position) ois.readObject();
            ois.close();

            System.out.println(result.toString());
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
