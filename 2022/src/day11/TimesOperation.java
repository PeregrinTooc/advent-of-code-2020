package day11;

import java.math.BigInteger;

public class TimesOperation extends Operation {

    @Override
    protected BigInteger apply(BigInteger item, Long amount) {
        return item.multiply(BigInteger.valueOf(amount));
    }

}