package algorithms.mazeGenerators;

/**
 * This abstract is group of maze generators with generate and measureAlgorithmTimeMillis functions.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * Generates new maze
     *
     * @param rows    - number of rows in the array
     * @param columns - number of columns in the array
     * @return New maze with the requested rows and columns
     */
    @Override
    abstract public Maze generate(int rows, int columns);

    /**
     * @param rows    - number of rows in the array
     * @param columns - number of columns in the array
     * @return the time that takes the function 'generate to run
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long Time = System.currentTimeMillis();
        generate(rows, columns);
        Time = System.currentTimeMillis() - Time;
        return Time;
    }
}
