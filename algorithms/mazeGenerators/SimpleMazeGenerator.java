package algorithms.mazeGenerators;
import java.util.Random;
public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        Random rn = new Random();
        int[][] simple_maze = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                simple_maze[i][j] = (rn.nextInt(10) + 1)%2;
            }
        }
        int i=0,j=0;
        while (i<rows-1 && j<columns-1)
        {
            simple_maze[i][j] = 0;
            if((rn.nextInt(10) + 1)%2==1)
                i++;
            else
                j++;
        }
        simple_maze[i][j] = 0;
        while (i!=rows-1 || j!=columns-1)
        {
            i=i+((i!=rows-1)?1:0);
            j=j+((j!=columns-1)?1:0);
            simple_maze[i][j] = 0;
        }

        return new Maze(simple_maze,new Position(0,0),new Position(rows-1,columns-1));
    }
}
