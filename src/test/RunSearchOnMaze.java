package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.Random;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
//        Maze maze = mg.generate(1000, 1000);
//        maze.print();
        System.out.println("maze done");
        Random t = new Random();
        long time = System.currentTimeMillis();
        while (true) {
            System.out.println("-------------------");
            int i = t.nextInt(5), j = t.nextInt(5);
            System.out.println(i + "," + j);
            Maze maze = mg.generate(i, j);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());
//            System.out.println("done");
        }
//        System.out.println(System.currentTimeMillis()-time);
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        //System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
//        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
//        for (int i = 0; i < solutionPath.size(); i++) {
//            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
//        }
        System.out.println(solutionPath.size());
        if (solutionPath.size() == 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}