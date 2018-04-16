package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator{

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long Time = System.currentTimeMillis();
        generate(rows,columns);
        Time = System.currentTimeMillis() - Time;
        return Time;
    }
}
