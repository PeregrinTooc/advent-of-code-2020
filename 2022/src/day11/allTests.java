package day11;

import day10.Solver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import util.Utils;

public class allTests {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;

    @BeforeAll
    static void classSetUp() {
        URL path = day10.allTests.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = day10.allTests.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    @Test
    void acceptance() {
        /*
         * String[] input = Utils.transform(testFile);
         * assertEquals(2, new Solver(input).solve1());
         * assertEquals(4, new Solver(input).solve2());
         * input = Utils.transform(realFile);
         * assertEquals(498, new Solver(input).solve1());
         * assertEquals(859, new Solver(input).solve2());
         */
    }

    @Test
    void firstTest() {
        assertEquals(1 + 1, 2);
    }

}