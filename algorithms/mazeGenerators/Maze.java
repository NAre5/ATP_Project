package algorithms.mazeGenerators;

public class Maze {
    public Maze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze.clone();//check
    }

    private int[][] maze;
}
