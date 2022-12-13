package day10;

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
        assertEquals(13140, new Solver(input).solve1());
        input = Utils.transform(realFile);
        assertEquals(13740, new Solver(input).solve1());
    }

    @Test
    void firstTest() {
        assertEquals(1 + 1, 2);
    }

}