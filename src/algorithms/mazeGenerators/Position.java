package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * this class represent a position in a two dimensional space
 */
public class Position implements Cloneable,Serializable {
    private int RowIndex;

    private int ColumnIndex;

    private Position parent;


    /**
     * c'tor
     *
     * @param RowIndex    - index for the x-coordinate
     * @param ColumnIndex - index for the y-coordinate
     */
    public Position(int RowIndex, int ColumnIndex) {
        this.RowIndex = RowIndex;
        this.ColumnIndex = ColumnIndex;
        parent = null;
    }

    /**
     * c'tor
     *
     * @param RowIndex    - index for the x-coordinate
     * @param ColumnIndex - index for the y-coordinate
     * @param parent      - another position(like the previous position)
     */
    public Position(int RowIndex, int ColumnIndex, Position parent) {
        this(RowIndex, ColumnIndex);
        this.parent = parent;
    }

    /**
     * @return current RowIndex
     */
    public int getRowIndex() {
        return RowIndex;
    }

    /**
     * @return current ColumnIndex
     */
    public int getColumnIndex() {
        return ColumnIndex;
    }


    /**
     * @return clone of the current position
     */
    public Position clone() {
        return new Position(RowIndex, ColumnIndex);
    }

    /**
     * @param x - a x-coordinate
     * @param y - a y-coordinate
     * @return if the current position have the same coordinates as the givens
     */
    public boolean equals(int x, int y) {
        return (this.RowIndex == x) && (this.ColumnIndex == y);
    }

    /**
     * @param obj - the object we want to compare to
     * @return if this object equals to the given object(by position comparing)
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Position &&
                this.RowIndex == ((Position) obj).RowIndex && this.ColumnIndex == ((Position) obj).ColumnIndex;
    }

    /**
     * @return the String description of this instance
     */
    @Override
    public String toString() {
        return "{" + RowIndex + "," + ColumnIndex + "}";
    }

    /**
     * the opposite position of the current instance is the position in other way from the parent position
     *
     * @return the opposite position of the instance
     */
    public Position opposite() {
        Integer r = RowIndex, c = ColumnIndex;
        Integer pr = parent.RowIndex, pc = parent.ColumnIndex;
        if (r.compareTo(pr) != 0)
            return new Position(r + r.compareTo(pr), c, this);
        if (c.compareTo(pc) != 0)
            return new Position(r, c + c.compareTo(pc), this);
        return null;
    }
}
