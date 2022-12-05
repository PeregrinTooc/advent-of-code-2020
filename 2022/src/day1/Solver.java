package day1;

import static util.Utils.splitAt;
import java.util.*;

class Solver {
    private List<List<String>> elves;

    public Solver(final String[] in) {
        elves = splitAt(in);
    }

    public long solve1() {
        ArrayList<Long> sortedElves = sortElvesByCarriedCalories();
        return sortedElves.get(sortedElves.size() - 1);
    }

    private ArrayList<Long> sortElvesByCarriedCalories() {
        var wrapper = new Object() {
            long value = 0;
            ArrayList<Long> values = new ArrayList<Long>();
        };
        ArrayList<Long> sortedElves = wrapper.values;
        elves.forEach(elve -> {
            wrapper.value = 0;
            elve.forEach(s -> {
                wrapper.value += Long.valueOf((String) s);
            });
            sortedElves.add(wrapper.value);
        });
        sortedElves.sort(null);
        return sortedElves;
    }

    public Long solve2() {
        ArrayList<Long> sortedElves = sortElvesByCarriedCalories();
        return sortedElves.get(sortedElves.size() - 1) + sortedElves.get(sortedElves.size() - 2)
                + sortedElves.get(sortedElves.size() - 3);
    }
}