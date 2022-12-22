package day11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Utils;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        void acceptance() {
                input = Utils.splitAt(Utils.transform(testFile));
                assertEquals(BigInteger.valueOf(10605L), new Solver(input).solve1());
                assertEquals(BigInteger.valueOf(2713310158L), new Solver(input).solve2());
                input = Utils.splitAt(Utils.transform(realFile));
                assertEquals(BigInteger.valueOf(78960L), new Solver(input).solve1());
                assertEquals(BigInteger.valueOf(14561971968L), new Solver(input).solve2());
        }

        @Test
        void oneMonkey() {
                input = new ArrayList<List<String>>();
                input.add(Arrays.asList(new String[] { "Monkey 0:",
                                "  Starting items: 79, 98",
                                "  Operation: new = old * 19",
                                "  Test: divisible by 23",
                                "    If true: throw to monkey 0",
                                "    If false: throw to monkey 0" }));
                var monkeyBusiness = new Solver(input).createMonkeyBusiness();
                TargetTest targetTest = new TargetTest(23L);
                Operation operation = Operation.create('*', 19L);
                Monkey theMonkey = Monkey.create(new Long[] { 79L, 98L }, operation, targetTest);
                targetTest.setTargets(theMonkey, theMonkey);
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                                new Monkey[] {
                                                theMonkey })));
                monkeyBusiness.tick();
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                                new Monkey[] {
                                                Monkey.create(new Long[] { 500L, 620L }, operation, targetTest) })));
                input.clear();
                input.add(Arrays.asList(new String[] { "Monkey 0:",
                                "  Starting items: 10, 20",
                                "  Operation: new = old + 10",
                                "  Test: divisible by 5",
                                "    If true: throw to monkey 0",
                                "    If false: throw to monkey 0" }));
                monkeyBusiness = new Solver(input).createMonkeyBusiness();
                targetTest = new TargetTest(5L);
                operation = Operation.create('+', 10L);
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                                new Monkey[] {
                                                Monkey.create(new Long[] { 10L, 20L }, operation, targetTest) })));
                monkeyBusiness.tick();
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                                new Monkey[] {
                                                Monkey.create(new Long[] { 6L, 10L }, operation, targetTest) })));
        }

        @Test
        void TwoMonkeys() {
                input = new ArrayList<List<String>>();
                input.add(Arrays.asList(new String[] { "Monkey 0:",
                                "  Starting items: 79, 98",
                                "  Operation: new = old * 19",
                                "  Test: divisible by 50",
                                "    If true: throw to monkey 1",
                                "    If false: throw to monkey 0" }));
                input.add(Arrays.asList(new String[] { "Monkey 1:",
                                "  Starting items:",
                                "  Operation: new = old + 10",
                                "  Test: divisible by 5",
                                "    If true: throw to monkey 1",
                                "    If false: throw to monkey 0" }));
                var monkeyBusiness = new Solver(input).createMonkeyBusiness();
                TargetTest firstTargetTest = new TargetTest(50L);
                TargetTest secondTargetTest = new TargetTest(5L);
                Operation timesOperation = Operation.create('*', 19L);
                Monkey theFirstMonkey = Monkey.create(new Long[] { 79L, 98L }, timesOperation, firstTargetTest);
                Operation plusOperation = Operation.create('+', 10L);
                Monkey theSecondMonkey = Monkey.create(new Long[0], plusOperation, secondTargetTest);
                firstTargetTest.setTargets(theSecondMonkey, theFirstMonkey);
                secondTargetTest.setTargets(theSecondMonkey, theFirstMonkey);
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                                new Monkey[] { theFirstMonkey, theSecondMonkey })));
                monkeyBusiness.tick();
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(
                                new Monkey[] { Monkey.create(new Long[] { 620L }, timesOperation, firstTargetTest),
                                                Monkey.create(new Long[] { 170L }, plusOperation,
                                                                secondTargetTest) })));
        }

        @Test
        void fourMonkeys() {
                input = Utils.splitAt(Utils.transform(testFile));
                var monkeyBusiness = new Solver(input).createMonkeyBusiness();
                var targetTests = new TargetTest[4];
                var monkeys = new Monkey[4];
                targetTests[0] = new TargetTest(23L);
                targetTests[1] = new TargetTest(19L);
                targetTests[2] = new TargetTest(13L);
                targetTests[3] = new TargetTest(17L);
                monkeys[0] = Monkey.create(new Long[] { 79L, 98L }, Operation.create('*', 19L), targetTests[0]);
                monkeys[1] = Monkey.create(new Long[] { 54L, 65L, 75L, 74L }, Operation.create('+', 6L),
                                targetTests[1]);
                monkeys[2] = Monkey.create(new Long[] { 79L, 60L, 97L }, Operation.create('*', "old"), targetTests[2]);
                monkeys[3] = Monkey.create(new Long[] { 74L }, Operation.create('+', 3L), targetTests[3]);
                targetTests[0].setTargets(monkeys[2], monkeys[3]);
                targetTests[0].setTargets(monkeys[2], monkeys[0]);
                targetTests[0].setTargets(monkeys[1], monkeys[3]);
                targetTests[0].setTargets(monkeys[0], monkeys[1]);
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(monkeys)));
                monkeyBusiness.tick();
                monkeys[0] = Monkey.create(new Long[] { 20L, 23L, 27L, 26L }, Operation.create('*', 19L),
                                targetTests[0]);
                monkeys[1] = Monkey.create(new Long[] { 2080L, 25L, 167L, 207L, 401L, 1046L },
                                Operation.create('+', 6L),
                                targetTests[1]);
                monkeys[2] = Monkey.create(new Long[] {}, Operation.create('*', "old"), targetTests[2]);
                monkeys[3] = Monkey.create(new Long[] {}, Operation.create('+', 3L), targetTests[3]);
                assertTrue(monkeyBusiness.equals(MonkeyBusiness.createFromMonkeys(monkeys)));
        }

}