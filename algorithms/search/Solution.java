package algorithms.search;

import java.util.ArrayList;

public class Solution {
    AState finalState;

    public Solution(AState finalState) {
        this.finalState = finalState;
    }

    public ArrayList<AState> getSolutionPath()
    {
        if (finalState==null)
            return null;
        ArrayList<AState> SolutionPath = new ArrayList<>();
        AState currentState = finalState;
        while (currentState!=null)
        {
            SolutionPath.add(0,currentState.clone());
            currentState = currentState.prevState;
        }
        return SolutionPath;
    }
}
