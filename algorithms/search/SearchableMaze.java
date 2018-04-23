package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable{
    private Maze maze;

    @Override
    public AState getStartState() {
        return new MazeState(maze.getStartPosition(),null);
    }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition(),null);
    }

    public SearchableMaze(Maze maze) {
        this.maze=maze;
    }



    /**
     * @param state
     * @return list of all the possible states that we can reach from the given state
     */
    @Override
    public List<AState> getAllPossibleStates(AState state) /*throws Exception*/ {
        //if (!(state instanceof MazeState))
        //    throw new Exception("");
        if (!(state instanceof MazeState))
            return null;


        Position mPosition = ((MazeState)state).getCurrent_position();
        List<AState> PossibleStates = new ArrayList<>();
        int[][] m = maze.getMaze();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0)
                    continue;
                try {
                    if (m[mPosition.getRowIndex() + x][mPosition.getColumnIndex() + y] == 1)
                        continue;
                } catch (Exception e) { // ignore ArrayIndexOutOfBounds
                    continue;
                }
                Position position = new Position(mPosition.getRowIndex() + x, mPosition.getColumnIndex() + y, mPosition);
                PossibleStates.add(new MazeState(position,state));
            }
        }

        return PossibleStates;
    }
}
