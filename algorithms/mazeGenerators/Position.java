package algorithms.mazeGenerators;

public class Position implements Cloneable{
    private int RowIndex;

    public int getRowIndex() {
        return RowIndex;
    }

    public int getColumnIndex() {
        return ColumnIndex;
    }

    private int ColumnIndex;

    private Position parent;

    public Position(int RowIndex, int ColumnIndex) {
        this.RowIndex = RowIndex;
        this.ColumnIndex = ColumnIndex;
    }

    public Position(int RowIndex, int ColumnIndex, Position parent)
    {
        this(RowIndex,ColumnIndex);
        this.parent=parent;
    }

    @Override
    public Position clone() {
        return new Position(RowIndex, ColumnIndex);
    }

    public boolean equals(int x,int y)
    {
        return (this.RowIndex ==x) && (this.ColumnIndex ==y);
    }

    @Override
    public String toString() {
        return "{"+RowIndex+","+ColumnIndex+"}";
    }

    public Position opposite() {
        Integer r=RowIndex,c=ColumnIndex;
        Integer pr=parent.RowIndex,pc=parent.ColumnIndex;
        if (r.compareTo(pr) != 0)
            return new Position(r + r.compareTo(pr), c, this);
        if (c.compareTo(pc) != 0)
            return new Position(r, c + c.compareTo(pc), this);
        return null;
    }
}
