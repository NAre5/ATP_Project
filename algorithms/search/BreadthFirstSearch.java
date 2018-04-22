package algorithms.search;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    /**
     * @param iSearchable
     * @return
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        //exception or return null
        AState start,goal;
        List<AState> nextStates,currentStates;
        List<String> allStates = new ArrayList<>();
        try {
            start = iSearchable.getStartState();
            goal = iSearchable.getGoalState();
            currentStates= iSearchable.getAllPossibleStates(start);
            currentStates.add(start);
        } catch (Exception e) {
            return null;
        }
        while (!currentStates.isEmpty())
        {
            nextStates = new ArrayList<>();
//            for (AState state : currentStates) {
//                if (goal.equals(state))
//                    return new Solution(state);
//                if (!allStates.contains(state.toString())) {
//                    allStates.add(state.toString());
//                    nextStates.addAll(iSearchable.getAllPossibleStates(state));
//                }
//            }
            for (int i = 0; i < currentStates.size(); i++) {
                if (goal.equals(currentStates.get(i))) {
                    this.NumberOfNodesEvaluated =0;/////////////////////
                    return new Solution(currentStates.get(i));
                }
                if (!allStates.contains(currentStates.get(i).toString())) {
                    allStates.add(currentStates.get(i).toString());
                    nextStates.addAll(iSearchable.getAllPossibleStates(currentStates.get(i)));
                }
            }

            currentStates=nextStates;
        }

        return null;//or new Solution(null)
    }

    /**
     * @return the name of the algorithm
     */
    @Override
    public String getName() {
        return "Breadth First Search";
    }


}
