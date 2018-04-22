package algorithms.search;

public abstract class AState{
    protected AState prevState;


    AState(AState prevState) {
        if (prevState!=null)
            this.prevState = prevState.clone();
    }

    abstract public String toString();
    abstract public boolean equals(Object obj);

    abstract protected AState clone();

}
