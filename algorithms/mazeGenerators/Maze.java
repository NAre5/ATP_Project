package algorithms.mazeGenerators;

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
