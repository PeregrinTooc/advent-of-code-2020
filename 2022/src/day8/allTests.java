package day8;

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

    private static void assertSolution1(String[] input, int expected) {
        assertEquals(expected, new Solver(input).solve1());
    }

    private static void assertSolution2(String[] input, int expected) {
        assertEquals(expected, new Solver(input).solve2());
    }

    @Test
    void acceptance() {
        String[] input = Utils.transform(testFile);
        assertSolution1(input, 21);
        assertSolution2(input, 8);
        input = Utils.transform(realFile);
        assertSolution1(input, 1560);
        assertSolution2(input, 252000);

    }

    @Test
    void directSurroundings() {
        assertSolution1(new String[]{"0"}, 1);
        assertSolution1(new String[]{"09", "90"}, 4);
        assertSolution1(new String[]{"000", "000", "000"}, 8);
        assertSolution1(new String[]{"000", "010", "000"}, 9);
        assertSolution1(new String[]{"010", "111", "010"}, 8);
        assertSolution1(new String[]{"000", "111", "010"}, 9);
        assertSolution1(new String[]{"010", "011", "010"}, 9);
        assertSolution1(new String[]{"010", "110", "010"}, 9);
        assertSolution1(new String[]{"010", "111", "000"}, 9);
    }

    @Test
    void squareOfFour() {
        assertSolution1(new String[]{"0000", "0110", "0110", "0000"}, 16);
        assertSolution1(new String[]{"1111", "1110", "0111", "1111"}, 14);
    }

    @Test
    void squareOfFive() {
        assertSolution1(new String[]{"00000", "01210", "02320", "01210", "00000"}, 25);
        assertSolution1(new String[]{"00300", "01310", "32323", "01310", "00300"}, 24);
        assertSolution1(new String[]{"99999", "91319", "92329", "91319", "99999"}, 16);
        assertSolution2(new String[]{"00000", "01210", "02320", "01210", "00000"}, 16);
        assertSolution2(new String[]{"00300", "01310", "33333", "01310", "00300"}, 4);
    }


}