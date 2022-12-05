package day4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.net.URL;

import util.Utils;

public class Day4Test {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;

    @BeforeAll
    static void classSetUp() {
        URL path = Day4Test.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = Day4Test.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    @Test
    void acceptance() {
        String[] input = Utils.transform(testFile);
        assertEquals(2, new Solver(input).solve1());
        assertEquals(4, new Solver(input).solve2());
        input = Utils.transform(realFile);
        assertEquals(498, new Solver(input).solve1());
        assertEquals(859, new Solver(input).solve2());
    }

    @Test
    void shouldCountCompleteInclusions() {
        assertOverlapIs(new String[] { "1-2,3-4" }, 0);
        assertOverlapIs(new String[] { "1-4,3-4" }, 1);
        assertOverlapIs(new String[] { "1-2,1-4" }, 1);
        assertOverlapIs(new String[] { "1-86,27-34" }, 1);
    }

    private void assertOverlapIs(String[] input, int expected) {
        assertEquals(expected, new Solver(input).solve1());
    }

}