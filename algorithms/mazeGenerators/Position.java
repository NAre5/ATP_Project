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

    public Position(int RowIndex, int ColumnIndex) {
        this.RowIndex = RowIndex;
        this.ColumnIndex = ColumnIndex;
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
}
