package Server;


import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    /**
     * This function will apply a server strategy
     *
     * @param inFromClient -an InputStream instance we will read the data from
     * @param outToClient  -an OutputStream instance we will write the data to
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(inFromClient);
            //ObjectOutputStream outputStream = new ObjectOutputStream(outToClient);
            int[] size_Coordinates = (int[])inputStream.readObject();//catch error if not int[2]
            IMazeGenerator generator = new MyMazeGenerator();
            Maze maze = generator.generate(size_Coordinates[0],size_Coordinates[1]);
            MyCompressorOutputStream outputStream = new MyCompressorOutputStream(outToClient);
            //or MyCompressorOutputStream outputStream = new MyCompressorOutputStream(outToClient.clone);
            outputStream.write(maze.toByteArray());

        }catch (Exception ignored)
        {

        }
    }
}
