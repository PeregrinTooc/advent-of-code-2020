package day7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
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
    @Disabled
    void acceptance() {
        String[] input = Utils.transform(testFile);
        var fileSystem = solver.parseAndGetFileSystem();
        var solver = new Solver(input);
        assertEquals(95437, solver.solve1(fileSystem));
        // assertEquals(95437, solver.solve2(fileSystem));
        input = Utils.transform(realFile);
        solver = new Solver(input);
        fileSystem = solver.parseAndGetFileSystem();
        assertEquals(0, solver.solve1(fileSystem));
        // assertEquals(859, new Solver(input).solve2());
    }

    @Test
    void emptyInputEmptyFilesSystem() {
        String[] input = new String[] { "$ cd /", "$ ls" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new AccumulatorImpl();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(0);
    }

    @Test
    void oneFileInRoot() {
        String[] input = new String[] { "$ cd /", "$ ls", "2 b" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new AccumulatorImpl();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(2);
    }

    @Test
    void twoFilesInRoot() {
        String[] input = new String[] { "$ cd /", "$ ls", "2 b", "3 c" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new AccumulatorImpl();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(5);
    }

    @Test
    void subDirectory() {
        String[] input = new String[] {
                "$ cd /", "$ ls",
                "2 b", "dir a",
                "$ cd a", "$ ls",
                "3 c" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new AccumulatorImpl();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(5);
        Solver.Directory directory = fileSystem.navigateTo("a");
        accumulator = new AccumulatorImpl();
        directory.getSize(accumulator);
        accumulator.assertIs(3);
    }

    private class AccumulatorImpl implements Accumulator {
        private int value = 0;

        public void assertIs(int i) {
            assertEquals(i, value);
        }

        @Override
        public void incrementBy(Integer size) {
            value += size;
        }
    }
}