package day13;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Solver {

    private final List<List<String>> input;

    public Solver(List<List<String>> input) {
        this.input = input;
    }

    public int solve1() {
        int result = 0;

        ListIterator<List<String>> iterator = input.listIterator();
        while (iterator.hasNext()) {
            List<String> strings = iterator.next();
            var left = NestedList.create(strings.get(0));
            var right = NestedList.create(strings.get(1));
            if (left.isSmallerThan(right)) {
                result += (iterator.previousIndex() + 1);
            }
        }

        return result;
    }

    public int solve2() {
        List<NestedList> packages = new ArrayList<NestedList>();
        var iterator = input.listIterator();
        packages.add(NestedList.create("[[2]]"));
        packages.add(NestedList.create("[[6]]"));
        while (iterator.hasNext()) {
            List<String> strings = iterator.next();
            packages.add(NestedList.create(strings.get(0)));
            packages.add(NestedList.create(strings.get(1)));
        }
        packages.sort((list1, list2) -> {
            return list1.isSmallerThan(list2) ? -1 : 1;
        });
        var iterator2 = packages.listIterator();
        var indexOf2 = 0;
        var indexOf6 = 0;
        while (iterator2.hasNext()) {
            var nestedList = iterator2.next();
            if (nestedList.toString().equals("[[6]]"))
                indexOf6 = iterator2.previousIndex() + 1;
            if (nestedList.toString().equals("[[2]]"))
                indexOf2 = iterator2.previousIndex() + 1;
        }
        return indexOf2 * indexOf6;
    }

}