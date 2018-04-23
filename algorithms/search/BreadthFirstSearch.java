package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> states;
    protected Queue<AState> tmp;

    public BreadthFirstSearch() {
        states = new LinkedList<>();
        tmp = new LinkedList<>();
    }

    /**
     * @param iSearchable
     * @return
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        //exception or return null
        NumberOfNodesEvaluated = 0;
        AState start, goal;
        List<String> allStates = new ArrayList<>();
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
                allStates.add(state.toString());
                states.addAll(iSearchable.getAllPossibleStates(state));
            }
            this.NumberOfNodesEvaluated++;


            tmp.addAll(states);
            while (!tmp.isEmpty())
                System.out.print(tmp.poll()+",");
            System.out.println("");
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
