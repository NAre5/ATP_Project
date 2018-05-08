package algorithms.search;

import java.util.List;

/**
 * This interface represent what all the searchable problem must implement.
 */
public interface ISearchable {

    /**
     * @return the start position of the problem.
     */
    AState getStartState();

    /**
     * @return the goal position of the problem.
     */
    AState getGoalState();

    /**
     * Enable to get next possible state from state.
     *
     * @param state - the state we want to evaluate and get the possible state from him
     * @return list of all the possible states that we can reach from the given state
     */
    List<AState> getAllPossibleStates(AState state);
}
