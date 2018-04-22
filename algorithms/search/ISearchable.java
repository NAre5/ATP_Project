package algorithms.search;

import java.util.List;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    /**
     * @param state
     * @return list of all the possible states that we can reach from the given state
     */
    List<AState> getAllPossibleStates(AState state);
}
