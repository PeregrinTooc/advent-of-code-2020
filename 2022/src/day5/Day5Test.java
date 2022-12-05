package day5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.net.URL;

import util.Utils;

public class Day5Test {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;

    @BeforeAll
    static void classSetUp() {
        URL path = Day5Test.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = Day5Test.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    @Test
    void acceptance() {
        String[] input = Utils.transform(testFile);
        Solver solver = new Solver(input);
        Solver.Parameters parameters = solver.transform();
        // assertEquals("CMZ", solver.solve1(parameters));
        // assertEquals(4, new Solver(input).solve2());
        input = Utils.transform(realFile);
        // assertEquals(498, new Solver(input).solve1());
        // assertEquals(859, new Solver(input).solve2());
    }

    @Test
    void shouldProjectInstructions() {
        var solver = new Solver();
        var stacks = new String[] { "[Z] [M] [P]", "1   2   3 " };
        var instructions = new String[] {};
        var parameters = solver.new Parameters(stacks, instructions);
        assertEquals("ZMP", solver.solve1(parameters));
    }

}
