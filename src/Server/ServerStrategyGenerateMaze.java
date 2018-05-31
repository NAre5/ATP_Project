package Server;


import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

/**
 * This class responsible for get the dimension for the maze from the client, compress it and send back to client.
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {


    /**
     * This function will apply a server strategy
     *
     * @param inFromClient -an InputStream instance we will read the data from
     * @param outToClient  -an OutputStream instance we will write the data to
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(inFromClient);
            outputStream = new ObjectOutputStream(outToClient);
            outputStream.flush();
            int[] size_Coordinates = (int[]) inputStream.readObject(); // read dimension of the maze from client.
            IMazeGenerator generator = (IMazeGenerator) Class.forName(Server.Configurations.getProperty(Server.Configurations.PROPERTIES.MAZE_GENERATION_ALGORITHM)).newInstance();//generate maze accorging to the dimension
            Maze maze = generator.generate(size_Coordinates[0], size_Coordinates[1]);
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();//Stream to write byte array.
            MyCompressorOutputStream out = new MyCompressorOutputStream(byteOut);
            out.write(maze.toByteArray());
            outputStream.writeObject(byteOut.toByteArray());//sending to client the maze.
        } catch (Exception ignored) {
        } finally { /*Safe close of the stream */
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
