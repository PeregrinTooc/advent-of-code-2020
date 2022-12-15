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
    void oneMonkey() {
        input = new ArrayList<List<String>>();
        input.add(Arrays.asList(new String[] { "Monkey 0:",
                "Starting items: 79, 98",
                "Operation: new = old * 19",
                "Test: divisible by 23",
                "  If true: throw to monkey 0",
                "  If false: throw to monkey 0" }));
        var monkeyBusiness = new Solver(input).createMonkeyBusiness();
        TargetTest targetTest = new TargetTest(23);
        Operation operation = Operation.create('*', 19);
        Monkey theMonkey = Monkey.create(new Integer[] { 79, 98 }, operation, targetTest);
        targetTest.setTargets(theMonkey, theMonkey);
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] {
                        theMonkey })));
        monkeyBusiness.tick();
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] {
                        Monkey.create(new Integer[] { 500, 620 }, operation, targetTest) })));
        input.clear();
        input.add(Arrays.asList(new String[] { "Monkey 0:",
                "Starting items: 10, 20",
                "Operation: new = old + 10",
                "Test: divisible by 5",
                "  If true: throw to monkey 0",
                "  If false: throw to monkey 0" }));
        monkeyBusiness = new Solver(input).createMonkeyBusiness();
        targetTest = new TargetTest(5);
        operation = Operation.create('+', 10);
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] {
                        Monkey.create(new Integer[] { 10, 20 }, operation, targetTest) })));
        monkeyBusiness.tick();
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] {
                        Monkey.create(new Integer[] { 6, 10 }, operation, targetTest) })));
    }

    @Test
    void TwoMonkeys() {
        input = new ArrayList<List<String>>();
        input.add(Arrays.asList(new String[] { "Monkey 0:",
                "Starting items: 79, 98",
                "Operation: new = old * 19",
                "Test: divisible by 50",
                "  If true: throw to monkey 1",
                "  If false: throw to monkey 0" }));
        input.add(Arrays.asList(new String[] { "Monkey 1:",
                "Starting items:",
                "Operation: new = old + 10",
                "Test: divisible by 5",
                "  If true: throw to monkey 1",
                "  If false: throw to monkey 0" }));
        var monkeyBusiness = new Solver(input).createMonkeyBusiness();
        TargetTest firstTargetTest = new TargetTest(50);
        TargetTest secondTargetTest = new TargetTest(5);
        Operation timesOperation = Operation.create('*', 19);
        Monkey theFirstMonkey = Monkey.create(new Integer[] { 79, 98 }, timesOperation, firstTargetTest);
        Operation plusOperation = Operation.create('+', 10);
        Monkey theSecondMonkey = Monkey.create(new Integer[0], plusOperation, secondTargetTest);
        firstTargetTest.setTargets(theFirstMonkey, theSecondMonkey);
        secondTargetTest.setTargets(theFirstMonkey, theSecondMonkey);
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] { theFirstMonkey, theSecondMonkey })));
        monkeyBusiness.tick();
        assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                new Monkey[] { Monkey.create(new Integer[] { 620, 170 }, timesOperation, firstTargetTest),
                        Monkey.create(new Integer[0], plusOperation, secondTargetTest) })));
    }

}