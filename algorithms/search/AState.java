package algorithms.search;

/**
 * This class represent a state in the problem. i.e. represent the current position of the player and allowing to get
 * the next possible states.
 */
public abstract class AState implements Comparable<AState> {
    protected AState prevState;// Through which state we reached to this state. Help to reproduce the solution.
    private double cost;//What is the cost to get this state.

    /**
     * c'tor
     *
     * @param prevState - Through which state we reached to this state.
     * @param cost      - What is the cost to get this state.
     */
    AState(AState prevState, double cost) {
        this.prevState = prevState;
        this.cost = cost;
    }

    /**
     * copy c'tor
     *
     * @param state - state that we create new state according to him.
     */
    AState(AState state) {
        this(state.prevState, state.getCost());
    }

    /**
     * @return the cost og get this state.
     */
    public double getCost() {
        return cost;
    }


    /**
     * @return description of the state cy string.
     */
    abstract public String toString();

    /**
     * @param obj another State that we want to is equal to this state.
     * @return true if obj is equal to this state.
     */
    abstract public boolean equals(Object obj);

    /**
     * Each concrete decide how to implement the comparison.
     *
     * @param o - another state that we want if he "bigger"/"smaller"/"equal" to this function
     * @return 0 if equal, -1 if smaller, 1 if bigger
     */
    abstract public int compareTo(AState o);

}
