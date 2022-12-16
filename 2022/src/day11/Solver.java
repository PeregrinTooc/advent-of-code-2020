package day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {

    private final class ActivityRecorderImplementation implements ActivityRecorder {
        private List<Long> activities = new ArrayList<Long>();

        @Override
        public BigInteger activity() {
            activities.sort(null);
            return BigInteger.valueOf(activities.get(activities.size() - 1))
                    .multiply(BigInteger.valueOf(activities.get(activities.size() - 2)));
        }

        @Override
        public void record(Long activity) {
            activities.add(activity);
        }
    }

    private List<List<String>> input;
    private Monkey[] monkeys;

    public Solver(List<List<String>> input) {
        this.input = input;
    }

    public BigInteger solve1() {
        var monkeyBusiness = createMonkeyBusiness();
        for (var i = 20; i-- > 0;) {
            monkeyBusiness.tick();
        }
        ActivityRecorder activityRecorder = new ActivityRecorderImplementation();
        monkeyBusiness.getActivity(activityRecorder);
        return activityRecorder.activity();
    }

    public BigInteger solve2() {
        int rest = 1;
        for (int i = 0; i < input.size(); i++) {
            rest *= Integer.valueOf(input.get(i).get(3).stripLeading().split(" ")[3]);
        }
        var monkeyBusiness = createMonkeyBusiness(1, rest);
        for (var i = 10000; i-- > 0;) {
            monkeyBusiness.tick();
        }
        ActivityRecorder activityRecorder = new ActivityRecorderImplementation();
        monkeyBusiness.getActivity(activityRecorder);
        return activityRecorder.activity();
    }

    private MonkeyBusiness createMonkeyBusiness(int divisor, int rest) {
        monkeys = new Monkey[input.size()];
        TargetTest[] targetTests = new TargetTest[input.size()];
        Operation[] operations = new Operation[input.size()];
        for (var i = 0; i < monkeys.length; i++) {
            targetTests[i] = extractTargetTest(i);
            operations[i] = extractOperation(i);
        }

        for (var i = 0; i < monkeys.length; i++) {
            monkeys[i] = Monkey.create(extractItems(i), operations[i], targetTests[i], divisor, rest);
        }
        for (var i = 0; i < monkeys.length; i++) {
            targetTests[i].setTargets(extractTrueTarget(i), extractFalseTarget(i));
        }
        return MonkeyBusiness.createFromMonkeys(
                monkeys);
    }

    public MonkeyBusiness createMonkeyBusiness() {
        return createMonkeyBusiness(3, Integer.MAX_VALUE);
    }

    private Monkey extractTrueTarget(int i) {
        return monkeys[Integer.valueOf(input.get(i).get(4).stripLeading().split(" ")[5])];
    }

    private Monkey extractFalseTarget(int i) {
        return monkeys[Integer.valueOf(input.get(i).get(5).stripLeading().split(" ")[5])];
    }

    private TargetTest extractTargetTest(int i) {
        TargetTest targetTest = new TargetTest(Long.valueOf(input.get(i).get(3).stripLeading().split(" ")[3]));
        return targetTest;
    }

    private Operation extractOperation(int i) {
        var op = input.get(i).get(2).stripLeading().split(" ")[4].charAt(0);
        String value = input.get(i).get(2).stripLeading().split(" ")[5];
        if (value.equals("old")) {
            return Operation.create(op, value);
        } else {
            return Operation.create(op, Long.valueOf(value));
        }
    }

    private Long[] extractItems(int i) {
        String[] split = input.get(i).get(1).stripLeading().split(" ", 3);
        if (split.length < 3) {
            return new Long[0];
        }
        var items = Arrays.asList(split[2].split(", ")).stream()
                .map(s -> Long.valueOf(s)).toList().toArray(new Long[] {});
        return items;
    }

}