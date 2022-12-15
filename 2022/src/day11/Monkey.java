package day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {

    private List<Integer> items;
    private Operation operation;
    private TargetTest targetTest;
    private Integer currentItem;

    private Monkey(Integer[] items, Operation operation, TargetTest targetTest) {
        this.items = new ArrayList<Integer>(List.of(items));
        this.operation = operation;
        this.targetTest = targetTest;
    }

    public static Monkey create(Integer[] items, Operation operation, TargetTest targetTest) {
        return new Monkey(items, operation, targetTest);
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
        var clonedItems = items.toArray(new Integer[]{});
        this.items.clear();
        for (int i = 0; i < clonedItems.length; i++) {
            currentItem = operation.apply(clonedItems[i]) / 3;
            targetTest.apply(this, currentItem);
        }
    }

    public void throwCurrentItemTo(Monkey target) {
        target.items.add(this.currentItem);
    }
}