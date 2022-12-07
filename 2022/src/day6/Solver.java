package day6;

import java.util.HashSet;
import java.util.Set;

public class Solver {

    public Solver() {
    }

    public int solve1(String signals) {
        return findBreakSignalIn(signals, 4);
    }

    public int solve2(String signals) {
        return findBreakSignalIn(signals, 14);
    }

    private int findBreakSignalIn(String signals, int packageSize) {
        for (int i = packageSize; i <= signals.length(); i++) {
            Set<String> s = new HashSet<String>();
            for (int j = i - packageSize; j < i; j++) {
                s.add(signals.substring(j, j + 1));
            }
            if (s.size() == packageSize) {
                return i;
            }
        }
        return signals.length();
    }

}
