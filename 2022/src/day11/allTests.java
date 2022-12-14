package day11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import util.Utils;

public class allTests {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;
    private List<List<String>> input;

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
        input = Utils.splitAt(Utils.transform(testFile));
        assertEquals(10605, new Solver(input).solve1());
        // assertEquals(4, new Solver(input).solve2());
        input = Utils.splitAt(Utils.transform(realFile));
        // assertEquals(498, new Solver(input).solve1());
        // assertEquals(859, new Solver(input).solve2());
    }

    @Test
    void firstTest() {
        input = new ArrayList<List<String>>();
        input.add(Arrays.asList(new String[] { "Monkey 0:",
                "Starting items: 79, 98",
                "Operation: new = old * 19",
                "Test: divisible by 23",
                "  If true: throw to monkey 0",
                "  If false: throw to monkey 0" }));
        var monkeyBusiness = new Solver(input).createMonkeyBusiness();
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] {
                        Monkey.create(new Integer[] { 79, 98 }, Operation.create('*', 19), new TargetTest(23)) })));
        monkeyBusiness.tick();
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] {
                        Monkey.create(new Integer[] { 500, 620 }, Operation.create('*', 19), new TargetTest(23)) })));

    }

}