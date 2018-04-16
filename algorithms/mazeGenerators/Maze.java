package algorithms.mazeGenerators;

/**
 * This class represents maze - A maze is a path or collection of paths, typically from an entrance to a goal.
 */
public class Maze {
    //The data structure that represent the maze
    private int[][] maze;

    public Maze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze.clone();//check
    }


    public Position getStartPosition()
    {
        return null;
    }
}
