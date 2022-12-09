package day7;

import static org.junit.Assert.assertEquals;

public class Accumulator implements SizableInfo {
    private int value = 0;

    public void assertIs(int i) {
        assertEquals(i, value);
    }

    @Override
    public void size(Integer size) {
        value += size;
    }

    public int getSize() {
        return value;
    }

    @Override
    public void size(SizableInfo sizableInfo) {
        sizableInfo.size(value);

    }
}