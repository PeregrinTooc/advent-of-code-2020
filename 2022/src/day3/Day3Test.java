package day3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.net.URL;

import util.Utils;

public class Day3Test {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;

    @BeforeAll
    static void classSetUp() {
        URL path = Day3Test.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = Day3Test.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    @Test
    void acceptance() {
        String[] input = Utils.transform(testFile);
        assertEquals(157, new Solver(input).solve1());
        assertEquals(70, new Solver(input).solve2());
        input = Utils.transform(realFile);
        assertEquals(8139, new Solver(input).solve1());
        assertEquals(2668, new Solver(input).solve2());
    }

    @Test
    void shouldFindTheDuplicateAndReturnThePrio() {
        assertEquals(1, new Solver(new String[] { "aa" }).solve1());
        assertEquals(2, new Solver(new String[] { "bb" }).solve1());
        assertEquals(27, new Solver(new String[] { "AA" }).solve1());
        assertEquals(27, new Solver(new String[] { "bAAC" }).solve1());
    }

    @Test
    void shouldFindTheCommonOfThreeAndReturnThePrio() {
        assertEquals(1, new Solver(new String[] { "ab", "ad", "Aa" }).solve2());
        assertEquals(2, new Solver(new String[] { "ab", "db", "bX" }).solve2());
    }

}