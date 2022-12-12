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
    @Disabled
    void acceptance() {

        String[] input = Utils.transform(testFile);
        assertEquals(13, new Solver(input).solve1());
        // assertEquals(4, new Solver(input).solve2());
        input = Utils.transform(realFile);
        // assertEquals(498, new Solver(input).solve1());
        // assertEquals(859, new Solver(input).solve2());

    }

    @Test
    void lessThanThreeInstructions() {
        assertCount(new String[] {}, 1);
        assertCount(new String[] { "R 1" }, 1);
        assertCount(new String[] { "R 2" }, 2);
        assertCount(new String[] { "R 2", "U 1" }, 2);
        assertCount(new String[] { "R 2", "U 2" }, 3);
    }

    private void assertCount(String[] input, int expectedResult) {
        Solver.mapOfHeadAndTail map = new Solver(input).new mapOfHeadAndTail();
        assertEquals(expectedResult, map.countTouchedSpots());
    }

}