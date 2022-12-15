package day11;

import java.util.Arrays;
import java.util.List;

public class Solver {

    private List<List<String>> input;
    private Monkey[] monkeys;

    public Solver(List<List<String>> input) {
        this.input = input;
    }

    public Integer solve1() {
        return null;
    }

    public Integer solve2() {
        return null;
    }

    public MonkeyBusiness createMonkeyBusiness() {
        monkeys = new Monkey[input.size()];
        TargetTest[] targetTests = new TargetTest[input.size()];
        for (int i = 0; i < monkeys.length; i++) {
            targetTests[i] = extractTargetTest(i);
        }
        for (int i = 0; i < monkeys.length; i++) {
            monkeys[i] = Monkey.create(extractItems(i), extractOperation(i), targetTests[i]);
        }
        for (int i = 0; i < monkeys.length; i++) {
            targetTests[i].setTargets(extractTrueTarget(i), extractFalseTarget(i));
        }
        return MonkeyBusiness.createFromMonkeys(
                monkeys);
    }

    private Monkey extractTrueTarget(int i) {
        return monkeys.length > 1 ? monkeys[1] : monkeys[0];
    }

    private Monkey extractFalseTarget(int i) {
        return monkeys[0];
    }

    private TargetTest extractTargetTest(int i) {
        var x = Integer.valueOf(input.get(i).get(3).split(" ")[3]);
        TargetTest targetTest = new TargetTest(x);
        return targetTest;
    }

    private Operation extractOperation(int i) {
        var op = input.get(i).get(2).split(" ")[4].charAt(0);
        var x = Integer.valueOf(input.get(i).get(2).split(" ")[5]);
        Operation operation = Operation.create(op, x);
        return operation;
    }

    private Integer[] extractItems(int i) {
        String[] split = input.get(i).get(1).split(" ", 3);
        if (split.length < 3) {
            return new Integer[0];
        }
        var items = Arrays.asList(split[2].split(", ")).stream()
                .map(s -> Integer.valueOf(s)).toList().toArray(new Integer[] {});
        return items;
    }

}