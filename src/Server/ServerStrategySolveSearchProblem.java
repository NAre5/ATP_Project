package Server;


import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {


    /**
     * This function will apply a server strategy
     *
     * @param inFromClient -an InputStream instance we will read the data from
     * @param outToClient  -an OutputStream instance we will write the data to
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        File theDir = new File("Solutions");
        theDir.mkdir();
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            File solution_file = new File("Solutions/" + maze.hashCode());
            if (solution_file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(solution_file.getPath()));
                Solution result = (Solution) ois.readObject();
                ois.close();
                outToClient.flush();
                toClient.writeObject(result);
            } else {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                ISearchingAlgorithm searcher = new DepthFirstSearch();
                Solution solution = searcher.solve(searchableMaze);

//                solution_file.getParentFile().mkdirs();
//                solution_file.createNewFile();
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(solution_file));
                outputStream.writeObject(solution);
                outputStream.close();
                outToClient.flush();
                toClient.writeObject(solution);
            }
            toClient.close();

        } catch (Exception ignored) {

        }

    }
}
