package algorithms.search;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represent a solution of problem.
 */
public class Solution implements Serializable{
    ArrayList<AState> SolutionPath;

    /**
     * c'tor
     *
     * @param finalState - the final state of the solution.
     */
    public Solution(AState finalState) {
        SolutionPath = new ArrayList<>();
        AState currentState = finalState;
        while (currentState != null) {//only the start position has null
            SolutionPath.add(0, currentState);//the prev state of each state must be before him un the solution.
            currentState = currentState.prevState;
        }
    }

    /**
     * @return list of the level to the final solution.
     */
    public ArrayList<AState> getSolutionPath() {return SolutionPath;}

    public boolean equals(Object obj)
    {
        if(obj instanceof Solution)
        {
            return this.getSolutionPath().equals(((Solution)obj).getSolutionPath());
        }
        return false;
    }
}
