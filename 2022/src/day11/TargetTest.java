package day11;

import java.math.BigInteger;

public class TargetTest {
    private Long test;
    private Monkey trueTarget;
    private Monkey falseTarget;

    public TargetTest(Long i) {
        test = i;
    }

    public Boolean equals(TargetTest other) {
        return this.test == other.test && this.trueTarget == other.trueTarget
                && this.falseTarget == other.falseTarget;
    }

    public void setTargets(Monkey trueTarget, Monkey falseTarget) {
        this.trueTarget = trueTarget;
        this.falseTarget = falseTarget;
    }

    public void apply(Monkey thrower, BigInteger item) {
        if (item.mod(BigInteger.valueOf(test)).equals(BigInteger.ZERO)) {
            thrower.throwCurrentItemTo(trueTarget);
        } else {
            thrower.throwCurrentItemTo(falseTarget);
        }
    }
}