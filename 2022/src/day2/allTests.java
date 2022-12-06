package day2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.net.URL;

import util.Utils;

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
        assertEquals(15, new Solver(input).solve1());
        assertEquals(12, new Solver(input).solve2());
        input = Utils.transform(realFile);
        assertEquals(13005, new Solver(input).solve1());
        assertEquals(11373, new Solver(input).solve2());
    }

    @Test
    void shouldCountPoints() {
        verifySolve1For(new String[] {}, 0);
        verifySolve1For(new String[] { "A X" }, 1 + 3);
        verifySolve1For(new String[] { "A Y" }, 2 + 6);
        verifySolve1For(new String[] { "A Z" }, 3 + 0);
        verifySolve1For(new String[] { "B X" }, 1 + 0);
        verifySolve1For(new String[] { "B Y" }, 2 + 3);
        verifySolve1For(new String[] { "B Z" }, 3 + 6);
        verifySolve1For(new String[] { "C X" }, 1 + 6);
        verifySolve1For(new String[] { "C Y" }, 2 + 0);
        verifySolve1For(new String[] { "C Z" }, 3 + 3);
        verifySolve1For(new String[] { "C Z", "A X" }, 6 + 4);
    }

    @Test
    void shouldCountPointsAfterFiguringOutTheCorrectDrawMove() {
        verifySolve2For(new String[] { "A Y" }, 1 + 3);
        verifySolve2For(new String[] { "B Y" }, 2 + 3);
        verifySolve2For(new String[] { "C Y" }, 3 + 3);
    }

    @Test
    void shouldCountPointsAfterFiguringOutTheCorrectLosingMove() {
        verifySolve2For(new String[] { "A X" }, 3 + 0);
        verifySolve2For(new String[] { "B X" }, 1 + 0);
        verifySolve2For(new String[] { "C X" }, 2 + 0);
    }

    @Test
    void shouldCountPointsAfterFiguringOutTheCorrectWinningMove() {
        verifySolve2For(new String[] { "A Z" }, 2 + 6);
        verifySolve2For(new String[] { "B Z" }, 3 + 6);
        verifySolve2For(new String[] { "C Z" }, 1 + 6);
    }

    private void verifySolve1For(String[] strategy, int expected) {
        solver = new Solver(strategy);
        assertEquals(expected, solver.solve1());
    }

    private void verifySolve2For(String[] strategy, int expected) {
        solver = new Solver(strategy);
        assertEquals(expected, solver.solve2());
    }
}
