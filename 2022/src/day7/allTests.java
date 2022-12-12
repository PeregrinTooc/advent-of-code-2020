package day7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        assertEquals(95437, solver.solve1(fileSystem));
        assertEquals(24933642, solver.solve2(fileSystem));
        input = Utils.transform(realFile);
        solver = new Solver(input);
        fileSystem = solver.parseAndGetFileSystem();
        assertEquals(1915606, solver.solve1(fileSystem));
        assertEquals(5025657, solver.solve2(fileSystem));
    }

    @Test
    void emptyInputEmptyFilesSystem() {
        String[] input = new String[] { "$ cd /", "$ ls" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new Accumulator();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(0);
    }

    @Test
    void oneFileInRoot() {
        String[] input = new String[] { "$ cd /", "$ ls", "2 b" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new Accumulator();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(2);
    }

    @Test
    void twoFilesInRoot() {
        String[] input = new String[] { "$ cd /", "$ ls", "2 b", "3 c" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new Accumulator();
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
        var accumulator = new Accumulator();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(5);
        Solver.Directory directory = fileSystem.navigateTo("a");
        accumulator = new Accumulator();
        directory.getSize(accumulator);
        accumulator.assertIs(3);
    }

    @Test
    void subSubDirectoryMap() {
        String[] input = new String[] {
                "$ cd /", "$ ls",
                "dir a",
                "$ cd a", "$ ls", "dir d",
                "$ cd d", };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var mapCreator = solver.new MapCreatorImpl();
        fileSystem.createMap(mapCreator);
        mapCreator.iterator().next();
        mapCreator.iterator().next();
        mapCreator.iterator().next();
        assertFalse(mapCreator.iterator().hasNext());
    }

    @Test
    void twoSubDirectories() {
        String[] input = new String[] {
                "$ cd /", "$ ls",
                "2 a", "dir b", "dir c",
                "$ cd b", "$ ls",
                "3 d",
                "$ cd ..", "$ cd c", "$ ls",
                "4 e.txt" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var accumulator = new Accumulator();
        fileSystem.getSize(accumulator);
        accumulator.assertIs(9);
        Solver.Directory directory = fileSystem.navigateTo("b");
        accumulator = new Accumulator();
        directory.getSize(accumulator);
        accumulator.assertIs(3);
        directory = fileSystem.navigateTo("c");
        accumulator = new Accumulator();
        directory.getSize(accumulator);
        accumulator.assertIs(4);
    }

    @Test
    void createDirectoryMap() {
        String[] input = new String[] {
                "$ cd /", "$ ls",
                "dir b", "dir c",
                "$ cd b", "$ cd ..", "$ cd c" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        var mapCreator = solver.new MapCreatorImpl();
        fileSystem.createMap(mapCreator);
        mapCreator.iterator().next();
        mapCreator.iterator().next();
        mapCreator.iterator().next();
        assertFalse(mapCreator.iterator().hasNext());
    }

    @Test
    void solver1() {
        String[] input = new String[] {
                "$ cd /", "$ ls",
                "90000 a", "dir b", "dir c",
                "$ cd b", "$ ls",
                "20000 d",
                "$ cd ..", "$ cd c", "$ ls",
                "30000 e.txt" };
        var solver = new Solver(input);
        var fileSystem = solver.parseAndGetFileSystem();
        assertEquals(50000, solver.solve1(fileSystem));
    }

}