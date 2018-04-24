package algorithms.search;

/**
 * This class is abstract class. He represent all searching algorithm and what they must to implement.
 * The common function is getNumberOfNodesEvaluated.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Integer NumberOfNodesEvaluated = null;// How much states we evaluated until get the solution.

    /**
     * @param iSearchable - search problem. Like 8-puzzle, maze etc.
     * @return the solution of the problem which contain the final state.
     */
    @Override
    public abstract Solution solve(ISearchable iSearchable);

    /**
     * @return the name of the algorithm
     */
    @Override
    public abstract String getName();

    /**
     * @return the number of states that reached and evaluated during the solving process.
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }
}

