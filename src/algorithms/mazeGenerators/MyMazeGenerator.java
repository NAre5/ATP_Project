package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * this class represent a complex maze generator, using prim's algorithm.
 */
public class MyMazeGenerator extends AMazeGenerator {
    /**
     * Generates new maze
     *
     * @param rows    - number of rows in the array
     * @param columns - number of columns in the array
     * @return New maze with the requested rows and columns
     */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            System.out.println("Maze size must be positive. 3X3 default maze has been created.");
            rows = 3;
            columns = 3;
        }
        int[][] maze = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = 1;
            }
        }
        Random rand = new Random();
        boolean vertical_flag = (rand.nextInt(10) + 1) % 2 == 1;
        boolean horizontal_flag = (rand.nextInt(10) + 1) % 2 == 1;//change name

        Position start = new Position(vertical_flag ? (horizontal_flag ? 0 : rows - 1) : (int) (Math.random() * rows),
                vertical_flag ? (int) (Math.random() * columns) : (horizontal_flag ? 0 : columns - 1), null);

        maze[start.getRowIndex()][start.getColumnIndex()] = 0;

        ArrayList<Position> frontier = new ArrayList<>();

        for (Position position : getCloseWalls(maze, start)) {
            frontier.add(position);
        }

        Position last = start.clone();
        while (!frontier.isEmpty()) {
            // pick current node at random
            Position current = frontier.remove((int) (Math.random() * frontier.size()));
            Position opposite = current.opposite();
            try {
                // if both node and its opposite are walls
                int cr = current.getRowIndex(), cc = current.getColumnIndex();
                int or = opposite.getRowIndex(), oc = opposite.getColumnIndex();
                if (maze[cr][cc] == 1) {
                    if (maze[or][oc] == 1) {

                        // open path between the nodes
                        maze[cr][cc] = 0;
                        maze[or][oc] = 0;

                        // store last node in order to mark it later
                        if ((opposite.getRowIndex() == 0 || opposite.getRowIndex() == rows - 1) || (opposite.getColumnIndex() == 0 || opposite.getColumnIndex() == columns - 1))
                            last = opposite.clone();
                        // iterate through direct neighbors of node, same as earlier

                        for (Position position : getCloseWalls(maze, opposite)) {
                            frontier.add(position);
                        }
                    }
                }
            } catch (Exception e) { // ignore NullPointer and ArrayIndexOutOfBounds
            }

        }
        return new Maze(maze, start, last);
    }

    /**
     * @param maze     - the maze we want to search in
     * @param position - the position we want to search from
     * @return list of all the walls positions surrounding the given position in the given maze
     */
    private ArrayList<Position> getCloseWalls(int[][] maze, Position position) {
        ArrayList<Position> frontier = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || x != 0 && y != 0)
                    continue;
                try {
                    if (maze[position.getRowIndex() + x][position.getColumnIndex() + y] == 0)
                        continue;
                } catch (Exception e) { // ignore ArrayIndexOutOfBounds
                    continue;
                }
                // add eligible points to frontier
                frontier.add(new Position(position.getRowIndex() + x, position.getColumnIndex() + y, position));
            }
        }
        return frontier;
    }


}
