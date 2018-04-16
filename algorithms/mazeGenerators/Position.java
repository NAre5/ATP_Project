package algorithms.mazeGenerators;

public class Position implements Cloneable{
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Position clone() {
        return new Position(x,y);
    }

    public boolean equals(int x,int y)
    {
        return (this.x==x) && (this.y==y);
    }

    public boolean equals(Position other)
    {
        return (this.x==other.x) && (this.y==other.y);
    }
}
