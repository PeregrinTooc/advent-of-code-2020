package day8;

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
        assertEquals(21, new Solver(input).solve1());
        // assertEquals(4, new Solver(input).solve2());
        // input = Utils.transform(realFile);
        // assertEquals(498, new Solver(input).solve1());
        // assertEquals(859, new Solver(input).solve2());

    }

    @Test
    void firstTest() {
        assertEquals(1, new Solver(new String[] { "0" }).solve1());
        assertEquals(4, new Solver(new String[] { "09", "90" }).solve1());
        assertEquals(8, new Solver(new String[] { "000", "000", "000" }).solve1());
        assertEquals(9, new Solver(new String[] { "000", "010", "000" }).solve1());
    }

}