package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     * @param rows
     * @param columns
     * @return New maze with the requested rows and columns
     */
    Maze generate(int rows, int columns);

    /**
     * @param rows
     * @param columns
     * @return the time that takes the function 'generate to run
     */
    long measureAlgorithmTimeMillis(int rows, int columns);
}
