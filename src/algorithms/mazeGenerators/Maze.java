package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.MazeState;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents maze - A maze is a path or collection of paths, typically from an entrance to a goal.
 */
public class Maze implements Serializable{
    //The data structure that represent the maze
    private int[][] maze;
    private Position start_position;
    private Position goal_position;

    public Maze(int[][] maze, Position start_position, Position goal_position) {
        assert maze != null && maze.length != 0;/////////////////////////////////////////
        this.maze = maze.clone();
        this.start_position = start_position.clone();
        this.goal_position = goal_position.clone();
    }

    public Maze(byte[] savedMazeBytes) {
        int[] sizes = new int[6];
        int i = 0;
        int sum = 0;
        for (int counter = 0; counter < 6; ) {
            sum += savedMazeBytes[i++]& 0xFF;

            if (savedMazeBytes[i] == 0) {
                sizes[counter++] = sum;
                sum = 0;
                i++;
            }
        }

        int startRow = sizes[0], startColumn = sizes[1];
        int goalRow = sizes[2], goalColumn = sizes[3];
        this.maze = new int[sizes[4]+Math.max(startRow, goalRow)][sizes[5]+Math.max(startColumn, goalColumn)];
        for (int r = 0; r < sizes[4]+Math.max(startRow, goalRow); r++) {
            for (int c = 0; c < sizes[5]+Math.max(startColumn, goalColumn); c++) {
                maze[r][c] = savedMazeBytes[i++];
            }
        }
        this.start_position = new Position(sizes[0],sizes[1]);
        this.goal_position = new Position(sizes[2],sizes[3]);
    }

    /**
     * @return clone of the maze
     */
    public int[][] getMaze() {
        return maze.clone();//check
    }

    /**
     * @return clone of the starting position
     */
    public Position getStartPosition() {
        return start_position.clone();
    }

    /**
     * @return clone of the goal position
     */
    public Position getGoalPosition() {
        return goal_position.clone();
    }

    /**
     * Prints the maze as: 0 for passage, 1 for wall, S for the starting position and E for the goal position
     */
    public void print() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (start_position.equals(i, j))
                    System.out.print('S');
                else if (goal_position.equals(i, j))
                    System.out.print('E');
                else
                    System.out.print(maze[i][j]);
                if (j != maze[0].length - 1)
                    System.out.print(' ');
            }
            System.out.println();
        }
    }

    /**
     * This function return all of the important data if the maze in 1D byte array. The data separated by 0.
     * @return
     */
    public byte[] toByteArray() {
        List<Byte> compressed = new ArrayList<Byte>();
        compressed.addAll(IntToByteList(this.start_position.getRowIndex()));//convert start row to byte array.
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(this.start_position.getColumnIndex()));//convert start column to byte array.
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(this.goal_position.getRowIndex()));//convert end row to byte array.
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(this.goal_position.getColumnIndex()));//convert end column to byte array.
        compressed.add((byte) 0);
        /*cthe data about the maze dimension is the complement of the max row/column of start/end to the real size.*/
        compressed.addAll(IntToByteList(maze.length - Math.max(this.start_position.getRowIndex(),this.goal_position.getRowIndex())));
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(maze[0].length - Math.max(this.start_position.getColumnIndex(),this.goal_position.getColumnIndex())));
        compressed.add((byte) 0);
        //adding the maze. 0 1
        for (int[] row : maze) {
            for (int cell : row) {
                compressed.add((byte) cell);
            }
        }
        //return the array as byte array.
        return toPrimitives(compressed.toArray(new Byte[compressed.size()]));
    }

    /**
     * Convert int to byte(0-255)
     * @param number - the number to be convert.
     * @return converted int to byte.
     */
    private List<Byte> IntToByteList(int number) {
        List<Byte> ByteList = new ArrayList<>();
        if (number%255!=0 || number==0)
            ByteList.add((byte) (number % 255));
        number -= number % 255;
        while (number > 0) {
            ByteList.add((byte) 255);
            number -= 255;
        }
        return ByteList;
    }

    /**
     * Return every cell in oBytes as byte(premitive)
     * @param oBytes
     * @return every cell in oBytes as byte(premitive)
     */
    private byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];
        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(toByteArray());
    }//check if really is hash(1<->1)
}
