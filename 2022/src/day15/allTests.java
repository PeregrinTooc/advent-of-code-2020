package day15;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Utils;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(26, new Solver(input).solve1(10));
        assertEquals(56000011L, new Solver(input).solve2(20));
        input = Utils.transform(realFile);
        assertEquals(5525847, new Solver(input).solve1(2000000));
        assertEquals(859, new Solver(input).solve2(4000000));
    }

    @Test
    void firstTest() {
        String input[] = new String[]{"Sensor at x=5, y=5: closest beacon is at x=6, y=5"};
        assertEquals(0, new Solver(input).solve1(3));
        assertEquals(1, new Solver(input).solve1(4));
        assertEquals(2, new Solver(input).solve1(5));
        input = new String[]{
                "Sensor at x=5, y=5: closest beacon is at x=6, y=5",
                "Sensor at x=5, y=1: closest beacon is at x=8, y=1"};
        assertEquals(2, new Solver(input).solve1(5));
        assertEquals(3, new Solver(input).solve1(3));
    }

}