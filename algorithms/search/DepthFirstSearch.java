package algorithms.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    /**
     * @param iSearchable
     * @return
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        this.NumberOfNodesEvaluated = 0;
        Stack<AState> states = new Stack<>();
        AState start, goal;
        List<String> visitedStates = new ArrayList<String>();
        start = iSearchable.getStartState();
        goal = iSearchable.getGoalState();
        states.push(start);
        while (!states.empty())
        {
            AState current = states.pop();
            if(goal.equals(current))
            {
                return new Solution(current);
            }
            if(!visitedStates.contains(current.toString()))
            {
                this.NumberOfNodesEvaluated++;
                visitedStates.add((current.toString()));
                states.addAll(iSearchable.getAllPossibleStates(current));

            }
        }
        return new Solution(null);
    }


    /**
     * @return the name of the algorithm
     */
    @Override
    public String getName() {
        return "Depth First Search";
    }

}
