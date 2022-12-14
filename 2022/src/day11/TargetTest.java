package day11;

public class TargetTest {
    int test;

    public TargetTest(int i) {
        test = i;
    }

    public Boolean equals(TargetTest other) {
        return this.test == other.test;
    }

}
