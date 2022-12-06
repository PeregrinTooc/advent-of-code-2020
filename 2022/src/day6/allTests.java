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
    void acceptance() {
        String testinput = Utils.transform(testFile)[0];
        String realInput = Utils.transform(realFile)[0];
        assertEquals(11, new Solver().solve1(testinput));
        assertEquals(1155, new Solver().solve1(realInput));
        assertEquals(26, new Solver().solve2(testinput));
        assertEquals(2789, new Solver().solve2(realInput));
    }

    @Test
    void returnPositionOfLastCharOfFirstGroupOf4DifferentCharacters() {
        assertEquals(5, new Solver().solve1("asdaf"));
        assertEquals(4, new Solver().solve1("asdf"));
        assertEquals(4, new Solver().solve1("asdfg"));
    }

    @Test
    void returnPositionOfLastCharOfFirstGroupOf14DifferentCharacters() {
        assertEquals(23, new Solver().solve2("bvwbjplbgvbhsrlpgdmjqwftvncz"));
        assertEquals(29, new Solver().solve2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
        assertEquals(26, new Solver().solve2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }
}