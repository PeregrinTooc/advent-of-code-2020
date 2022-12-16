package day11;

import java.math.BigInteger;

public class PlusOperation extends Operation {

    @Override
    protected BigInteger apply(BigInteger item, Long amount) {
        return item.add(BigInteger.valueOf(amount));
    }
}