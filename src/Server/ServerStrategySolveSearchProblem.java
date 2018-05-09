package Server;


import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategySolveSearchProblem implements IServerStrategy {


    /**
     * This function will apply a server strategy
     *
     * @param inFromClient -an InputStream instance we will read the data from
     * @param outToClient  -an OutputStream instance we will write the data to
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            MyDecompressorInputStream inputStream = new MyDecompressorInputStream(inFromClient);
            Maze maze = new Maze(inputStream.readAllBytes());
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            ISearchingAlgorithm searcher = new DepthFirstSearch();
            Solution solution = searcher.solve(searchableMaze);


            ObjectOutputStream outputStream = new ObjectOutputStream(outToClient);
            outputStream.writeObject(solution);
            //save the solution for the specific maze
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");

        }catch (Exception ignored)
        {

        }

    }
}
