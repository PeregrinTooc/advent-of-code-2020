package day16;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
    @Disabled
    void acceptance() {
        String[] input = Utils.transform(testFile);
        assertEquals(1651, new Solver(input).solve1());
        //assertEquals(4, new Solver(input).solve2());
        input = Utils.transform(realFile);
        //assertEquals(498, new Solver(input).solve1());
        //assertEquals(859, new Solver(input).solve2());
    }

    @Test
    void firstTest() {
        String[] input = new String[]{"Valve AA has flow rate=0; tunnels lead to valves BB, CC",
                "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
                "Valve CC has flow rate=2; tunnels lead to valves AA, BB"};
        assertEquals(28 * 13 + 26 * 2, new Solver(input).solve1());
    }

}