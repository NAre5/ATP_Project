package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position current_position;

    public MazeState(Position current_position, AState prevState) {
        super(prevState);
        this.current_position = current_position.clone();

    }

    public Position getCurrent_position() {
        return current_position.clone();
    }

    @Override
    public String toString() {
        return current_position.toString();
    }

//    @Override
//    public boolean equals(Object obj) {//recursive
//        return obj!=null && obj instanceof MazeState &&
//                cost == ((AState) obj).cost && current_position.equals(((MazeState) obj).current_position) &&
//                ((prevState==null && ((MazeState) obj).prevState==null) || prevState.equals(((MazeState) obj).prevState));
//    }

    @Override
    public boolean equals(Object obj) {//recursive
        return obj!=null && obj instanceof MazeState &&
                current_position.equals(((MazeState) obj).current_position);
    }



    //public boolean SameAs(AState state)?

    @Override
    protected AState clone() {//recursive
        if (prevState==null)
            return new MazeState(current_position.clone(),null);
        return new MazeState(current_position.clone(),prevState.clone());
    }
}
