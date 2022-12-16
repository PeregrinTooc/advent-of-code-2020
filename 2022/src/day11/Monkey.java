package day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Monkey {

    private List<BigInteger> items = new ArrayList<BigInteger>();
    private Operation operation;
    private TargetTest targetTest;
    private BigInteger currentItem;
    private Long activity = 0L;
    private int divisor = 3;
    private BigInteger rest = BigInteger.valueOf(Integer.MAX_VALUE);

    private Monkey(Long[] items, Operation operation, TargetTest targetTest) {
        for (Long item : items) {
            this.items.add(BigInteger.valueOf(item));
        }
        this.operation = operation;
        this.targetTest = targetTest;
    }

    public static Monkey create(Long[] items, Operation operation, TargetTest targetTest) {
        return new Monkey(items, operation, targetTest);
    }

    public static Monkey create(Long[] items, Operation operation, TargetTest targetTest, int divisor, int rest) {
        var monkey = create(items, operation, targetTest);
        monkey.divisor = divisor;
        monkey.rest = BigInteger.valueOf(rest);
        return monkey;
    }

    public Boolean equals(Monkey other) {
        if (this.items.size() != other.items.size()) {
            return false;
        }
        for (int i = 0; i < items.size(); i++) {
            if (!this.items.get(i).equals(other.items.get(i))) {
                return false;
            }
        }
        return this.operation == other.operation;
    }

    public void investigateItems() {
        var clonedItems = items.toArray(new BigInteger[] {});
        this.items.clear();
        for (int i = 0; i < clonedItems.length; i++) {
            if (activity == Long.MAX_VALUE)
                assert 1 == 2;
            activity++;
            currentItem = operation.apply(clonedItems[i]).divide(BigInteger.valueOf(this.divisor));
            currentItem = currentItem.compareTo(rest) < 0 ? currentItem : currentItem.remainder(rest);
            targetTest.apply(this, currentItem);
        }
    }

    public void throwCurrentItemTo(Monkey target) {
        target.items.add(this.currentItem);
    }

    public void getActivity(ActivityRecorder activityRecorder) {
        activityRecorder.record(activity);
    }
}