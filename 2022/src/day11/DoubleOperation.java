package day11;

class DoubleOperation extends Operation {
    @Override
    protected int apply(Integer item, int amount2) {
        return 2 * item;
    }
}