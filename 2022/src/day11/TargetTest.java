package day11;

public class TargetTest {
    private int test;
    private Monkey trueTarget;
    private Monkey falseTarget;

    public TargetTest(int i) {
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

}
