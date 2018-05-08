package algorithms.search;

/**
 * This interface represent all searching algorithm and what they must to implement.
 */
public interface ISearchingAlgorithm {
    /**
     * @param iSearchable - search problem. Like 8-puzzle, maze etc.
     * @return the solution of the problem which contain the final state.
     */
    Solution solve(ISearchable iSearchable);

    /**
     * @return the name of the algorithm
     */
    String getName();

    /**
     * @return the number of states that reached and evaluated during the solving process.
     */
    int getNumberOfNodesEvaluated();
}
