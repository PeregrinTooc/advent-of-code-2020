package day9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.net.URL;

import util.Utils;

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

    @Test
    void acceptance() {

        String[] input = Utils.transform(testFile);
        assertEquals(13, new Solver(input).solve1());
        // assertEquals(4, new Solver(input).solve2());
        input = Utils.transform(realFile);
        assertEquals(498, new Solver(input).solve1());
        // assertEquals(859, new Solver(input).solve2());

    }

    @Test
    void noOverlaps() {
        assertCount(new String[] {}, 1);
        assertCount(new String[] { "R 1" }, 1);
        assertCount(new String[] { "R 2" }, 2);
        assertCount(new String[] { "R 2", "U 1" }, 2);
        assertCount(new String[] { "R 2", "U 2" }, 3);
        assertCount(new String[] { "R 2", "U 2", "R 2" }, 4);
    }

    @Test
    void backAndForth() {
        assertCount(new String[] { "R 2", "U 1", "L 1" }, 2);
        assertCount(new String[] { "R 2", "U 1", "L 2" }, 2);
    }

    @Test
    void incrementalAcceptance() {
        assertCount(new String[] { "R 4" }, 4);
        assertCount(new String[] { "R 4", "U 4" }, 7);
        assertCount(new String[] { "R 4", "U 4", "L 3" }, 9);
        assertCount(new String[] { "R 4", "U 4", "L 3", "D 1" }, 9);
        assertCount(new String[] { "R 4", "U 4", "L 3", "D 1", "R 4" }, 10);
    }

    private void assertCount(String[] input, int expectedResult) {
        assertEquals(expectedResult, new Solver(input).solve1());
    }

}