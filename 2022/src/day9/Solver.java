package day9;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

public class Solver {

    private List<Pair<Direction, Integer>> instructions;

    public Solver(String[] input) {
        instructions = new ArrayList<Pair<Direction, Integer>>();
        for (int i = 0; i < input.length; i++) {
            String[] s = input[i].split(" ");
            instructions.add(new Pair<Direction, Integer>(null, Integer.valueOf(s[1])));
        }
    }

    public int solve1() {
        return 0;
    }

    public class mapOfHeadAndTail {

        public int countTouchedSpots() {
            int result = 1;
            if (instructions.size() > 0) {
                result = instructions.get(0).getValue1();
            }
            if (instructions.size() > 1) {
                result += instructions.get(1).getValue1() - 1;
            }
            return result;
        }

    }

    private class Direction {
    }

}
