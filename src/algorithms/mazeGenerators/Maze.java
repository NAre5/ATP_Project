package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.MazeState;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents maze - A maze is a path or collection of paths, typically from an entrance to a goal.
 */
public class Maze {
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

    public byte[] toByteArray() {
        //byte[] size_row = new byte[(int) Math.ceil(maze.length/255)];
        //byte[] size_column = new byte[(int) Math.ceil(maze[0].length/255)];
        List<Byte> compressed = new ArrayList<Byte>();
        compressed.addAll(IntToByteList(this.start_position.getRowIndex()));
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(this.start_position.getColumnIndex()));
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(this.goal_position.getRowIndex()));
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(this.goal_position.getColumnIndex()));
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(maze.length - Math.max(this.start_position.getRowIndex(),this.goal_position.getRowIndex())));
        compressed.add((byte) 0);
        compressed.addAll(IntToByteList(maze[0].length - Math.max(this.start_position.getColumnIndex(),this.goal_position.getColumnIndex())));
        compressed.add((byte) 0);

        // need to be in MyCompreeser
        /*boolean switch_flag = false;
        int last_bit = 0;
        byte counter = 0;
        for (int[] row : maze) {
            for (int cell : row) {
                if (cell == last_bit) {
                    if (counter == 127) {
                        compressed.add(counter);
                        compressed.add((byte) 0);
                        counter = 0;
                    }
                    counter++;//add if counter too big
                }
                else {
                    compressed.add(counter);
                    last_bit = (last_bit + 1) % 2;
                    counter = 1;
                }
            }
        }
        compressed.add(counter);
        */
        for (int[] row : maze) {
            for (int cell : row) {
                compressed.add((byte) cell);
            }
        }

//        compressed.add((byte) 0);
//        compressed.add((byte) 0);


        //check one change from list to byte array
        return toPrimitives(compressed.toArray(new Byte[compressed.size()]));
    }

    private List<Byte> IntToByteList(int number) {
        List<Byte> ByteList = new ArrayList<>();
        ByteList.add((byte) (number % 255));
        number -= number % 255;
        while (number > 0) {
            ByteList.add((byte) 255);
            number -= 255;
        }
        return ByteList;
    }

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
    }
}
