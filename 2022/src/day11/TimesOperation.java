package day11;

public class TimesOperation extends Operation {

    @Override
    protected int apply(Integer item, int amount) {
        return item * amount;
    }

}