package algorithms.search;

import java.util.ArrayList;

/**
 * This class represent a solution of problem.
 */
public class Solution {
    AState finalState;

    /**
     * c'tor
     *
     * @param finalState - the final state of the solution.
     */
    public Solution(AState finalState) {
        this.finalState = finalState;
    }

    /**
     * @return list of the level to the final solution.
     */
    public ArrayList<AState> getSolutionPath() {
        if (finalState == null)
            return null;
        ArrayList<AState> SolutionPath = new ArrayList<>();
        AState currentState = finalState;
        while (currentState != null) {//only the start position has null
            SolutionPath.add(0, currentState);//the prev state of each state must be before him un the solution.
            currentState = currentState.prevState;
        }
        return SolutionPath;
    }
}
