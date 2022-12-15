package day11;

public class Monkey {

    private Integer[] items;
    private Operation operation;
    private TargetTest targetTest;

    private Monkey(Integer[] items, Operation operation, TargetTest targetTest) {
        this.items = items.clone();
        this.operation = operation;
        this.targetTest = targetTest;
    }

    public Boolean equals(Monkey other) {
        if (this.items.length != other.items.length) {
            return false;
        }
        for (int i = 0; i < items.length; i++) {
            if (!this.items[i].equals(other.items[i])) {
                return false;
            }
        }
        if (this.operation != other.operation) {
            return false;
        }

        return this.targetTest.equals(other.targetTest);
    }

    public static Monkey create(Integer[] items, Operation operation, TargetTest targetTest) {
        return new Monkey(items, operation, targetTest);
    }

    public void investigateItems() {
        for (int i = 0; i < items.length; i++) {
            items[i] = operation.apply(items[i]) / 3;
        }
    }

}
