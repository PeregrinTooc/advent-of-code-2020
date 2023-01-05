package day14;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Utils;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class allTests {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;

    @BeforeAll
    static void classSetUp() {
        URL path = allTests.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = allTests.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    @BeforeEach
    void setUp() {
        solver = new Solver(Utils.transform(testFile));
    }

    @Test
    void acceptance() {
        String[] input = Utils.transform(testFile);
        assertEquals(24, solver.solve1());
        assertEquals(93, solver.solve2());
        input = Utils.transform(realFile);
        assertEquals(1298, new Solver(input).solve1());
        assertEquals(25585, new Solver(input).solve2());
    }

    @Test
    void initializeMap() {
        assertOccupied(498, 4);
        assertOccupied(498, 6);
        assertOccupied(498, 5);
        assertOccupied(497, 6);
        assertFalse(solver.isOccupied(new Point(499, 4)));
    }

    private void assertOccupied(int x, int y) {
        assertTrue(solver.isOccupied(new Point(x, y)));
    }

    @Test
    void fillWithSand() {
        assertEquals(24, solver.solve1());
        assertOccupied(500, 8);
        assertOccupied(499, 8);
        assertOccupied(501, 8);
        assertOccupied(500, 7);
        assertOccupied(498, 8);
        assertOccupied(500, 2);
        assertOccupied(497, 5);
        assertOccupied(495, 8);
    }

}