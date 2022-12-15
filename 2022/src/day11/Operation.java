package day11;

public abstract class Operation {

    private static Operation timesOperation = new TimesOperation();
    private static Operation squareOperation = new SquareOperation();
    private static Operation plusOperation = new PlusOperation();
    private static Operation doubleOperation = new DoubleOperation();
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

    public static Operation create(char operation, String old) {
        switch (operation) {
            case '+':
                return doubleOperation;
            default:
                return squareOperation;
        }
    }

    public final int apply(Integer item) {
        return apply(item, amount);
    }

    protected abstract int apply(Integer item, int amount2);

}