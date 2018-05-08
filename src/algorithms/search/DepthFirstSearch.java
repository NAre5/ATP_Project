package algorithms.search;

import java.util.HashSet;
import java.util.Stack;

/**
 * This class represent Depth First Search about search problem.
 * The algorithm begin from the start position and evaluated each time one of his neighbors(N). And after that evaluate
 * one of N's neighbors. Reapting until reached the goal position.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    /**
     * This algorithm evaluate state by getting one of his neihgbors and call the function again with the selected
     * neighbor. But instead of using recursion this function use stack.
     * Repeating this routine until get the goal position.
     *
     * @param iSearchable - search problem that we want to solve with BFS.
     * @return solution of iSearchable
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        this.NumberOfNodesEvaluated = 0;
        Stack<AState> states = new Stack<>();
        AState start, goal;
        HashSet<String> visitedStates = new HashSet<>();//Contain all the states the algorithm evaluated.
        start = iSearchable.getStartState();
        goal = iSearchable.getGoalState();
        states.push(start);
        while (!states.empty()) {
            AState current = states.pop();//get the first node that reached until now.
            if (goal.equals(current)) {

                return new Solution(current);
            }
            if (!visitedStates.contains(current.toString())) {//get the first node that reached until now.
                this.NumberOfNodesEvaluated++;
                visitedStates.add((current.toString()));
                states.addAll(iSearchable.getAllPossibleStates(current));//get all neighbors of state.
            }
        }
        return null;//if we don't reached the goal state.
    }


    /**
     * @return the name of the algorithm
     */
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
