package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> states;

    public BreadthFirstSearch() {
        states = new PriorityQueue<>();
    }

    /**
     * @param iSearchable
     * @return
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        if(iSearchable == null)
            return null;
        //exception or return null
        NumberOfNodesEvaluated = 0;
        AState start, goal;
        HashSet<String> allStates = new HashSet<>();
        //List<String> allStates = new ArrayList<>();
        try {
            start = iSearchable.getStartState();
            goal = iSearchable.getGoalState();

            //states.addAll(iSearchable.getAllPossibleStates(start));
            states.add(start);

        } catch (Exception e) {
            states.clear();
            return null;
        }
        //while (!currentStates.isEmpty())
        while (!states.isEmpty()) {
            AState state = states.poll();
            if (goal.equals(state)) {
                states.clear();
                return new Solution(state);
            }
            if (!allStates.contains(state.toString())) {
                this.NumberOfNodesEvaluated++;
                allStates.add(state.toString());
                states.addAll((iSearchable.getAllPossibleStates(state)));
                //states.addAll(iSearchable.getAllPossibleStates(state));
                //for (AState aState : iSearchable.getAllPossibleStates(state)) {
                  //  if (!allStates.contains(aState.toString())) {
                    //    states.add(aState);
                    //}
                //}
            }
        }
        states.clear();/////////////////////
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
