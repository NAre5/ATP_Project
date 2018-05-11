package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;

/**
 * This class extend AState. It is represent state of maze. The representation is by the current position of the player.
 */
public class MazeState extends AState implements Serializable {
    private Position current_position;//THe current position in the maze;

    /**
     * c'tor
     *
     * @param current_position - The current position.
     * @param prevState        - The Maze state we come from to the current position.
     * @param cost             - The cost to get this position
     */
    public MazeState(Position current_position, AState prevState, double cost) {
        super(prevState, cost);
        this.current_position = current_position.clone();

    }


    /**
     * @return clone of current position.
     */
    public Position getCurrent_position() {
        return current_position.clone();
    }

    /**
     * @return description of the state by string.
     */
    @Override
    public String toString() {
        return current_position.toString();
    }

    /**
     * @param obj another State that we want to is equal to this state.
     * @return true if obj is current position.
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof MazeState &&
                current_position.equals(((MazeState) obj).current_position);
    }

    /**
     * compare between to maze states according to the difference between each state to his prevState.
     *
     * @param o - another state that we want if he "bigger"/"smaller"/"equal" to this function
     * @return Double.compare between the difference between each state to his prevState.
     */
    @Override
    public int compareTo(AState o) {
        if (!(o instanceof MazeState))
            return 0;
        return Double.compare(getCost(), o.getCost());
    }

}
