package algorithms.mazeGenerators;

/**
 * Interface for any class who wants to be Maze generator.
 */
public interface IMazeGenerator {
    /**
     * @param rows - number of rows in the array
     * @param columns - number of columns in the array
     * @return New maze with the requested rows and columns
     */
    Maze generate(int rows, int columns);

    /**
     * @param rows - number of rows in the array
     * @param columns - number of columns in the array
     * @return the time that takes the function 'generate to run
     */
    long measureAlgorithmTimeMillis(int rows, int columns);

}
