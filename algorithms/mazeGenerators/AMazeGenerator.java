package algorithms.mazeGenerators;

/**
 * This abstract is group of maze generators with generate and measureAlgorithmTimeMillis functions.
 */
public abstract class AMazeGenerator implements IMazeGenerator{

    @Override
    /**
     * @param rows - number of rows in the array
     * @param columns - number of columns in the array
     * @return New maze with the requested rows and columns
     */
    public abstract Maze generate(int rows, int columns);

    @Override
    /**
     * @param rows - number of rows in the array
     * @param columns - number of columns in the array
     * @return the time that takes the function 'generate to run
     */
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long Time = System.currentTimeMillis();
        generate(rows,columns);
        Time = System.currentTimeMillis() - Time;
        return Time;
    }

}
