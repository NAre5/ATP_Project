package algorithms.search;

public class SearchAlgorithmFactory {
    private SearchAlgorithmFactory(){}
    public static ISearchingAlgorithm create_search_algorithm(String className)
    {
        switch (className)
        {
            case "DepthFirstSearch":
                new DepthFirstSearch();
            case "BreadthFirstSearch":
                new BreadthFirstSearch();
            case "BestFirstSearch":
                new BestFirstSearch();
            default:
                throw new IllegalArgumentException("className should be real class name that implements ISearchingAlgorithm");

        }
    }
}
