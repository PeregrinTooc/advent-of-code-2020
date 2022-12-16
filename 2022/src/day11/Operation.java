package day11;

import java.math.BigInteger;
import java.util.HashMap;

public abstract class Operation {

    private static Operation squareOperation = new SquareOperation();
    private static HashMap<Long, Operation> plusOperations = new HashMap<Long, Operation>();
    private static HashMap<Long, Operation> timesOperations = new HashMap<Long, Operation>();
    private Long amount = 0L;

    public static Operation create(char operation, Long x) {
        switch (operation) {
            case '+':
                if (plusOperations.containsKey(x))
                    return plusOperations.get(x);
                else {
                    Operation plusOperation = new PlusOperation();
                    plusOperation.amount = x;
                    plusOperations.put(x, plusOperation);
                    return plusOperation;
                }
            default:
                if (timesOperations.containsKey(x))
                    return timesOperations.get(x);
                else {
                    Operation timesOperation = new TimesOperation();
                    timesOperation.amount = x;
                    timesOperations.put(x, timesOperation);
                    return timesOperation;
                }
        }

    }

    public static Operation create(char operation, String old) {
        return squareOperation;
    }

    public final BigInteger apply(BigInteger item) {
        return apply(item, amount);
    }

    protected abstract BigInteger apply(BigInteger item, Long amount);
}