package algorithms.mazeGenerators;

public class Maze {
    //The data structure that represent the maze
    private int[][] maze;
    private Position start_position;
    private Position goal_position;

    public Maze(int[][] maze, Position start_position, Position goal_position) {
        this.maze = maze.clone();
        this.start_position = start_position.clone();
        this.goal_position = goal_position.clone();
    }

    public int[][] getMaze() {
        return maze.clone();//check
    }


    public Position getStartPosition()
    {
        return start_position.clone();
    }

    public Position getGoalPosition()
    {
        return goal_position.clone();
    }
}
