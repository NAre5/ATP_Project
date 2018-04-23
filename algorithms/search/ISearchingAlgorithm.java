package algorithms.search;

public interface ISearchingAlgorithm {
    /**
     * @param iSearchable
     * @return
     */
    Solution solve(ISearchable iSearchable);

    /**
     * @return the name of the algorithm
     */
    String getName();

    /**
     * @return
     */
    int getNumberOfNodesEvaluated();//check if ok with %s
}
