package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Integer NumberOfNodesEvaluated = null;

    /**
     * @param iSearchable
     * @return
     */
    @Override
    public abstract Solution solve(ISearchable iSearchable);

    /**
     * @return the name of the algorithm
     */
    @Override
    public abstract String getName();

    /**
     * @return
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        //if (NumberOfNodesEvaluated==null)
        //    throw new Exception("");
        return NumberOfNodesEvaluated;
    }
}

