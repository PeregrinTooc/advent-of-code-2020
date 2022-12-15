package day11;

public abstract class Operation {

    static Operation timesOperation = new TimesOperation();
    static Operation plusOperation = new PlusOperation();
    private int amount = 0;

    public static Operation create(char operation, int x) {
        switch (operation) {
            case '+':
                plusOperation.amount = x;
                return plusOperation;
            default:
                timesOperation.amount = x;
                return timesOperation;
        }

    }

    public final int apply(Integer item) {
        return apply(item, amount);
    }

    protected abstract int apply(Integer item, int amount2);

}
