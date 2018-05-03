package algorithms.mazeGenerators;

import java.util.Random;

/**
 * This class represent a simple maze generator. i.e. a maze that intialize with 0 and 1 randomally.
 * In addition we ensure that the maze has a solution.
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    /**
     * Generates new maze
     *
     * @param rows    - number of rows in the array
     * @param columns - number of columns in the array
     * @return New maze with the requested rows and columns
     */
    @Override
    public Maze generate(int rows, int columns) {
        if(rows<=0||columns<=0)
        {
            System.out.println("Maze size must be positive. 3X3 default maze has been created.");
            rows=3;
            columns=3;
        }
        Random rn = new Random();
        int[][] simple_maze = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                simple_maze[i][j] = (rn.nextInt(10) + 1) % 2;//Put 0 or 1 randomly.
            }
        }
        int i = 0, j = 0;
        //we enter to the body if we not at bound of the array.
        while (i < rows - 1 && j < columns - 1) {
            simple_maze[i][j] = 0;
            if ((rn.nextInt(10) + 1) % 2 == 1)//we move right or down randomly.
                i++;
            else
                j++;
        }
        simple_maze[i][j] = 0;
        //we enter the body if we in the bound of the array.
        while (i != rows - 1 || j != columns - 1) {
            i = i + ((i != rows - 1) ? 1 : 0);
            j = j + ((j != columns - 1) ? 1 : 0);
            simple_maze[i][j] = 0;
        }

        return new Maze(simple_maze, new Position(0, 0), new Position(rows - 1, columns - 1));
    }
}
