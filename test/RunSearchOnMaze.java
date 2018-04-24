package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        //IMazeGenerator mg = new SimpleMazeGenerator();
        Maze maze = mg.generate(10, 10);
        long Time = System.currentTimeMillis();
        //maze.Print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        System.out.println("BreFS time:"+(System.currentTimeMillis() - Time));
        Time = System.currentTimeMillis();
        solveProblem(searchableMaze, new DepthFirstSearch());
        System.out.println("DFS time:" + (System.currentTimeMillis() - Time));
        Time = System.currentTimeMillis();
        solveProblem(searchableMaze, new BestFirstSearch());
        System.out.println("Best time:"+(System.currentTimeMillis() - Time));
        Time = System.currentTimeMillis();
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: " +
                "%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        //for (int i = 0; i < solutionPath.size(); i++) {
        //  System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        //}
    }
}