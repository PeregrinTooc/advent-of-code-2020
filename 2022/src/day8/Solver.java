package day8;

import org.jetbrains.annotations.NotNull;

public class Solver {

    private final int[][] mapOfTrees;

    public Solver(final String @NotNull [] input) {
        mapOfTrees = new int[input.length][input.length];
        for (int i = 0; i < mapOfTrees.length; i++) {
            for (int j = 0; j < mapOfTrees[i].length; j++) {
                mapOfTrees[i][j] = Integer.valueOf(input[i].substring(j, j + 1));
            }
        }
    }

    public Integer solve1() {
        if (mapOfTrees.length <= 1) {
            return 1;
        }
        int result = 2 * this.mapOfTrees.length + 2 * (this.mapOfTrees.length - 2);
        for (int i = 1; i < this.mapOfTrees.length - 1; i++) {
            for (int j = 1; j < this.mapOfTrees.length - 1; j++) {
                if (isBiggerThanSurroundings(i, j))
                    result += 1;
            }
        }
        return result;
    }

    private boolean isBiggerThanSurroundings(int i, int j) {
        return isBiggerThanAllLeft(i, j) || isBiggerThanAllRight(i, j)
                || isBiggerThanAllAbove(i, j) || isBiggerThanAllBelow(i, j);
    }

    private boolean isBiggerThanAllBelow(int i, int j) {
        for (int k = j + 1; k < mapOfTrees.length; k++) {
            if (this.mapOfTrees[i][j] <= this.mapOfTrees[i][k]) {
                return false;
            }
        }
        return true;
    }

    private boolean isBiggerThanAllAbove(int i, int j) {
        for (int k = j - 1; k >= 0; k--) {
            if (this.mapOfTrees[i][j] <= this.mapOfTrees[i][k]) {
                return false;
            }
        }
        return true;
    }

    private boolean isBiggerThanAllRight(int i, int j) {
        for (int k = i + 1; k < mapOfTrees.length; k++) {
            if (this.mapOfTrees[i][j] <= this.mapOfTrees[k][j]) {
                return false;
            }
        }
        return true;
    }

    private boolean isBiggerThanAllLeft(int i, int j) {
        for (int k = i - 1; k >= 0; k--) {
            if (this.mapOfTrees[i][j] <= this.mapOfTrees[k][j]) {
                return false;
            }
        }
        return true;
    }

    public int solve2() {
        return 0;
    }
}