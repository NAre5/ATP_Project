package algorithms.search;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represent Breadth First Search about search problem.
 * The algorithm begin from the start position and evaluated all his neighbors. And after that evaluate his neighbors in
 * evaluation order. Repeating this until reached the goal position.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> states;// Contain all states the algorithm reached.

    /**
     * c'tor
     */
    public BreadthFirstSearch() {
        states = new LinkedList<>();
    }

    /**
     * The algorithm begin from the start position and evaluated all his neighbors. And after that evaluate his neighbors
     * in evaluation order. Repeating this until reached the goal position.
     *
     * @param iSearchable - search problem that we want to solve with BFS.
     * @return solution of iSearchable
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        if (iSearchable == null)
            return null;
        NumberOfNodesEvaluated = 0;
        AState start, goal;
        HashSet<String> visitedStates = new HashSet<>();//Contain all the states the algorithm evaluated.
        start = iSearchable.getStartState();
        goal = iSearchable.getGoalState();
        states.add(start);
        while (!states.isEmpty()) {
            AState state = states.poll();//get the first node that reached until now.
            if (goal.equals(state)) {
                states.clear();//necessary if we will want to solve another with this algorithm.
                return new Solution(state);
            }
            if (!visitedStates.contains(state.toString())) {//if we evaluated state we don't want to evaluate him again.
                this.NumberOfNodesEvaluated++;
                visitedStates.add(state.toString());
                states.addAll((iSearchable.getAllPossibleStates(state)));//get all neighbors of state.
            }
        }
        states.clear();
        return null;
    }

    /**
     * @return the name of the algorithm
     */
    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
