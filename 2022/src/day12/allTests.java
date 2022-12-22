package day12;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Utils;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class allTests {
    static File testFile = null;
    static File realFile = null;

    @BeforeAll
    static void classSetUp() {
        URL path = allTests.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = allTests.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    private static Map getHeightMap(String[] input) {
        var solver = new Solver(input);
        Map actual = solver.createMap();
        return actual;
    }

    private static void createAndAssertEquals(String[] input, int[][] heights) {
        var simpleMapWithoutStart = getHeightMap(input);
        var heightMap = Map.create(heights);
        assertEquals(simpleMapWithoutStart, heightMap);
    }

    @Test
    void acceptance() {
        String[] input = Utils.transform(testFile);
        assertEquals(31, new Solver(input).solve1());
        assertEquals(29, new Solver(input).solve2());
        input = Utils.transform(realFile);
        assertEquals(490, new Solver(input).solve1());
        assertEquals(488, new Solver(input).solve2());
    }

    @Test
    void mapCreation() {
        createAndAssertEquals(new String[] { "a" }, new int[][] { { 0 } });
        createAndAssertEquals(new String[] { "S" }, new int[][] { { 0 } });
        createAndAssertEquals(new String[] { "E" }, new int[][] { { 25 } });
        createAndAssertEquals(
                new String[] {
                        "Sbcdefaa",
                        "ghijklaa",
                        "mnopqraa",
                        "stuvwxaa",
                        "yEaaaaaa",
                        "xwvabaaa" },
                new int[][] {
                        { 0, 1, 2, 3, 4, 5, 0, 0 },
                        { 6, 7, 8, 9, 10, 11, 0, 0 },
                        { 12, 13, 14, 15, 16, 17, 0, 0 },
                        { 18, 19, 20, 21, 22, 23, 0, 0 },
                        { 24, 25, 0, 0, 0, 0, 0, 0 },
                        { 23, 22, 21, 0, 1, 0, 0, 0 } });
    }

}