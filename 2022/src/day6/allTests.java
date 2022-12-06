package day6;

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
    private Solver solver = new Solver();

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
        assertEquals(11, new Solver().solve1(input[0]));
    }
}