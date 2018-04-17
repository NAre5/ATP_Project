package algorithms.mazeGenerators;

import java.util.ArrayList;

public class MyMazeGenerator extends AMazeGenerator {
    /**
     * @param rows    - number of rows in the array
     * @param columns - number of columns in the array
     * @return New maze with the requested rows and columns
     */
    @Override
    public Maze generate(int rows, int columns) {
        int[][] maze = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = 1;
            }
        }
        Position start = new Position((int)(Math.random() * rows), (int)(Math.random() * columns), null);

        ArrayList< Position > frontier = new ArrayList <> ();

        func(frontier,maze,start);

        Position last = null;

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
                        last = opposite.clone();

                        // iterate through direct neighbors of node, same as earlier
                        func(frontier,maze,opposite);
                    }
                }
            } catch (Exception e) { // ignore NullPointer and ArrayIndexOutOfBounds
            }
            if (frontier.isEmpty())
                System.out.println();
        }
        System.out.println();
        return new Maze(maze,start,last);
    }

    private void func(ArrayList<Position> frontier, int[][] maze, Position position)
    {
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
    }


}
