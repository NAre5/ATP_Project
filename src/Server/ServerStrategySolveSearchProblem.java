package Server;


import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

/**
 * This class responsible for getting maze from client and return the Solution.
 * The method:
 * for any new maze we create a file with name liked the hashcode.
 * if this maze was sending in the past we just read the solution from the file and send back.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {


    /**
     * This function will apply a server strategy
     *
     * @param inFromClient -an InputStream instance we will read the data from
     * @param outToClient  -an OutputStream instance we will write the data to
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        File theDir = new File("Solutions"); // In this directory we add new Solution files.
        theDir.mkdir();
        ObjectInputStream fromClient = null;
        ObjectOutputStream toClient = null;
        ObjectInputStream ois = null;
        ObjectOutputStream outputStream = null;
        try {
            fromClient = new ObjectInputStream(inFromClient);
            toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();//Read the maze from the client.
            File solution_file = new File("Solutions/" + maze.hashCode());//The path of the new file is the hash code for this maze
            if (solution_file.exists()) {//if the file exists, it is means we solve thus maze in the past and we just need to read the file.
                ois = new ObjectInputStream(new FileInputStream(solution_file.getPath()));
                Solution result = (Solution) ois.readObject();
                outToClient.flush();
                toClient.writeObject(result);//send the solution to client
            } else {//if we did not solve this maze in past.
                //Solve the maze
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                ISearchingAlgorithm searcher = (ISearchingAlgorithm) Class.forName(Server.Configurations.getProperty(Server.Configurations.PROPERTIES.SOLVE_ALGORITHM)).getConstructor().newInstance();
                Solution solution = searcher.solve(searchableMaze);
                outputStream = new ObjectOutputStream(new FileOutputStream(solution_file));
                outputStream.writeObject(solution);//write the solution to new file
                outToClient.flush();
                toClient.writeObject(solution);//send the solution to client
            }
        } catch (Exception ignored) {
        }finally { /*Safe close of the streams */
            try {
                if (fromClient != null)
                    fromClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (toClient != null)
                    toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ois != null)
                    ois.close();
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
