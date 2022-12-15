package day11;

class SquareOperation extends Operation {
    @Override
    protected int apply(Integer item, int amount2) {
        return item * item;
    }
}