package day11;

import java.math.BigInteger;

class SquareOperation extends Operation {
    @Override
    protected BigInteger apply(BigInteger item, Long amount2) {
        return item.multiply(item);
    }
}