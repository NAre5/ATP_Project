package algorithms.search;

public abstract class AState implements Comparable<AState> {
    protected AState prevState;
    private double cost;

    AState(AState prevState, double cost) {
        if (prevState != null)
            this.prevState = prevState;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    abstract public String toString();

    abstract public boolean equals(Object obj);

    abstract public int compareTo(AState o);

    abstract protected AState clone();

}
