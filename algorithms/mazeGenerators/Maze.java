package algorithms.mazeGenerators;

/**
 * This class represents maze - A maze is a path or collection of paths, typically from an entrance to a goal.
 */
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

    public void print()
    {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j <maze[0].length; j++) {
                if(start_position.equals(i,j))
                    System.out.print('S');
                else if (goal_position.equals(i,j))
                    System.out.print('E');
                else
                    System.out.print(maze[i][j]);
                if (j!=maze[0].length-1)
                    System.out.print(' ');
            }
            System.out.println();
        }
    }

}
