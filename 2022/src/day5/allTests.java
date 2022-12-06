package day5;

import org.junit.jupiter.api.BeforeAll;
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
        String[] input = Utils.transform(testFile);
        solver = new Solver(input);
        Solver.Parameters parameters = solver.transform();
        assertEquals("CMZ", solver.solve1(parameters));
        parameters = solver.transform();
        assertEquals("MCD", solver.solve2(parameters));
        input = Utils.transform(realFile);
        solver = new Solver(input);
        parameters = solver.transform();
        assertEquals("RLFNRTNFB", solver.solve1(parameters));
        parameters = solver.transform();
        assertEquals("MHQTLJRLB", solver.solve2(parameters));
    }

    @Test
    void emptyInstructionsNoChange() {
        var parameters = solver.new Parameters(
                new String[] { "[Z] [M] [P]", "1   2   3    " },
                new String[] {});
        assertEquals("ZMP", solver.solve1(parameters));
    }

    @Test
    void oneEmptyStack() {
        var parameters = solver.new Parameters(
                new String[] { "[Z]     [P]", "1   2   3    " },
                new String[] {});
        assertEquals("Z P", solver.solve1(parameters));
    }

    @Test
    void higherStack() {
        var parameters = solver.new Parameters(
                new String[] { "[A] [N] [Q]", "[Z] [M] [P]", "1   2   3    " },
                new String[] {});
        assertEquals("ANQ", solver.solve1(parameters));
    }

    @Test
    void moveOne() {
        var parameters = solver.new Parameters(
                new String[] { "[Z] [P]    ", "1   2   3    " },
                new String[] { "move 1 from 2 to 3" });
        assertEquals("Z P", solver.solve1(parameters));
    }

    @Test
    void oneHigherStack() {
        var parameters = solver.new Parameters(
                new String[] { "        [A]", "[Z] [M] [P]", "1   2   3    " },
                new String[] {});
        assertEquals("ZMA", solver.solve1(parameters));
    }

    @Test
    void swapAndBack() {
        var parameters = solver.new Parameters(
                new String[] { "[Z] [M] [P]", "1   2   3" },
                new String[] {
                        "move 1 from 2 to 1",
                        "move 1 from 1 to 2", });
        assertEquals("ZMP", solver.solve1(parameters));
    }

    @Test
    void swapTwo() {
        var parameters = solver.new Parameters(
                new String[] { "[Z] [M] [P]", "1   2   3" },
                new String[] {
                        "move 1 from 2 to 1",
                        "move 1 from 3 to 2",
                        "move 1 from 1 to 3" });
        assertEquals("ZPM", solver.solve1(parameters));
    }

    @Test
    void moveMultiple() {
        assertEquals("B P", solver.solve1(solver.new Parameters(
                new String[] { "[Z] [M] [P]", "[A] [B] [C]", "1   2   3" },
                new String[] {
                        "move 2 from 2 to 1",
                })));
        assertEquals("M P", solver.solve2(solver.new Parameters(
                new String[] { "[Z] [M] [P]", "[A] [B] [C]", "1   2   3" },
                new String[] {
                        "move 2 from 2 to 1",
                })));
    }

}
