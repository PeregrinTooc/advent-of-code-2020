package day11;

public class PlusOperation extends Operation {

    @Override
    protected int apply(Integer item, int amount) {
        return item + amount;
    }
}