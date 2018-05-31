package Server;


import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

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
            ObjectOutputStream outputStream = new ObjectOutputStream(outToClient);
            outputStream.flush();
            int[] size_Coordinates = (int[])inputStream.readObject();//catch error if not int[2]
            IMazeGenerator generator = new MyMazeGenerator();
            Maze maze = generator.generate(size_Coordinates[0],size_Coordinates[1]);
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            MyCompressorOutputStream out = new MyCompressorOutputStream(byteOut);
            //or MyCompressorOutputStream outputStream = new MyCompressorOutputStream(outToClient.clone);
            out.write(maze.toByteArray());
            outputStream.writeObject(byteOut.toByteArray());
//
        }catch (Exception ignored)
        {

        }
    }
}
