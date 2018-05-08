package JUnit;


import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This JUnit check BestFs algorithm.
 */
class JUnitTestingBestFirstSearch {
    @org.junit.jupiter.api.Test
    /**
     * Check the getName method.
     */
    void getName() {
        assertEquals("BestFirstSearch", new BestFirstSearch().getName());
    }

    @org.junit.jupiter.api.Test
    /**
     * Check how it deal with Isearchable null parameter.
     */
    void test1() {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        ISearchable searchableMaze = null;
        ASearchingAlgorithm Best = new BestFirstSearch();
        assertEquals(null, Best.solve(searchableMaze));
    }

}