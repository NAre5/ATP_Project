package algorithms.search;

import java.util.PriorityQueue;

/**
 * This class extend BreadthFS by using Priority Queue instead of regular queue.
 * The comparison of the queue is according to compareTo function in AState.
 */
public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * c'tor
     */
    public BestFirstSearch() {
        states = new PriorityQueue<>();
    }

    /**
     * @return the name of the algorithm
     */
    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
