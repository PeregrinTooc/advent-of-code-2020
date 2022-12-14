package day11;

public class Operation {
    static private Operation timesOperation = new Operation();
    private int amount;

    public static Operation create(char operation, int x) {
        timesOperation.amount = x;
        return timesOperation;
    }

    public int apply(Integer integer) {
        return integer * amount;
    }

}
