package day11;

import java.util.Arrays;
import java.util.List;

public class Solver {

    private List<List<String>> input;

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
        return MonkeyBusiness.createFromMonkeys(
                new Monkey[] { Monkey.create(extractItems(), extractOperation(), extractTargetTest()) });
    }

    private TargetTest extractTargetTest() {
        var x = Integer.valueOf(input.get(0).get(3).split(" ")[3]);
        TargetTest targetTest = new TargetTest(x);
        return targetTest;
    }

    private Operation extractOperation() {
        var op = input.get(0).get(2).split(" ")[4].charAt(0);
        var x = Integer.valueOf(input.get(0).get(2).split(" ")[5]);
        Operation operation = Operation.create('+', 19);
        return operation;
    }

    private Integer[] extractItems() {
        var items = Arrays.asList(input.get(0).get(1).split(" ", 3)[2].split(", ")).stream()
                .map(s -> Integer.valueOf(s)).toList().toArray(new Integer[] {});
        return items;
    }

}