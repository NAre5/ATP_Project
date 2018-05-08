package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent extand ISearchable. Represent a maze problem. This is adapter between maze to searchable program.
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;// the maze we want to solve.

    /**
     * c'tor
     *
     * @param maze - The maze that the adapter contain.
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    /**
     * return the start position of the maze. return as Maze state with cost 0.
     */
    public AState getStartState() {
        return new MazeState(maze.getStartPosition(), null, 0);
    }

    @Override
    /**
     * return the goal position of the maze. return as Maze state with cost 0.
     */
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition(), null, 0);
    }

    /**
     * @param state - Maze state that we want to get all his neighbors.
     * @return list of all the possible states that we can reach from the given state
     */
    @Override
    public List<AState> getAllPossibleStates(AState state) {

        if (!(state instanceof MazeState))
            return null;
        Position mPosition = ((MazeState) state).getCurrent_position();
        List<AState> PossibleStates = new ArrayList<>();//contain all possible state from state.
        int[][] m = maze.getMaze();
        int counter = 0;
        //search in the surrounding cells.
        for (int y = 1; y >= -1; y--) {
            for (int x = -1; x <= 1; x++) {
                if (x == 0 && y == 0)//this is the param state.
                    continue;
                boolean isDiagonal = false;
                try {//maybe out the maze.
                    if (m[mPosition.getRowIndex() + x][mPosition.getColumnIndex() + y] == 1)
                        continue;
                    isDiagonal = ((Math.abs(x) + Math.abs(y)) == 2);//the abs sum of the index of diagonal is 2.(-1,-1),(1,-1)...
                    if (isDiagonal && (m[mPosition.getRowIndex() + x][mPosition.getColumnIndex()] == 1)//we can move diagonal only if we can reached the cell with regular moves.
                            && (m[mPosition.getRowIndex()][mPosition.getColumnIndex() + y] == 1))
                        continue;
                } catch (Exception e) { // ignore ArrayIndexOutOfBounds
                    continue;
                }
                Position position = new Position(mPosition.getRowIndex() + x, mPosition.getColumnIndex() + y, mPosition);//legal postion that can be reached from state.
                PossibleStates.add(isDiagonal ? PossibleStates.size() : counter, new MazeState(position, state, state.getCost() + (isDiagonal ? Math.sqrt(2) : 1)));
                counter = isDiagonal ? counter : ++counter;
            }
        }
        return PossibleStates;
    }
}
