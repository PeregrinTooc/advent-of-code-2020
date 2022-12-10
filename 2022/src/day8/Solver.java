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
                if (isBiggerThanSurroundings(i, j)) result += 1;
            }
        }
        return result;
    }

    public int solve2() {
        int result = 0;
        for (int i = 1; i < mapOfTrees.length; i++) {
            for (int j = 0; j < mapOfTrees.length; j++) {
                result = (calculateScenicViewFor(i, j) < result ? result : calculateScenicViewFor(i, j));
            }
        }
        return result;
    }

    private int calculateScenicViewFor(int i, int j) {
        return numberOfVisibleTreesAbove(i, j) * numberOfVisibleTreesBelow(i, j) * numberOfVisibleTreesToRight(i, j) * numberOfVisibleTreesToLeft(i, j);
    }

    private int numberOfVisibleTreesBelow(int i, int j) {
        int result = 0;
        for (int k = i - 1; k >= 0; k--) {
            result++;
            if (mapOfTrees[i][j] <= mapOfTrees[k][j]) {
                break;
            }
        }
        return result;
    }

    private int numberOfVisibleTreesToRight(int i, int j) {
        int result = 0;
        for (int k = j + 1; k < mapOfTrees.length; k++) {
            result++;
            if (mapOfTrees[i][j] <= mapOfTrees[i][k]) {
                break;
            }
        }
        return result;
    }

    private int numberOfVisibleTreesToLeft(int i, int j) {
        int result = 0;
        for (int k = j - 1; k >= 0; k--) {
            result++;
            if (mapOfTrees[i][j] <= mapOfTrees[i][k]) {
                break;
            }
        }
        return result;
    }

    private int numberOfVisibleTreesAbove(int i, int j) {
        int result = 0;
        for (int k = i + 1; k < mapOfTrees.length; k++) {
            result++;
            if (mapOfTrees[k][j] >= mapOfTrees[i][j]) {
                break;
            }
        }
        return result;
    }

    private boolean isBiggerThanSurroundings(int i, int j) {
        return isBiggerThanAllLeft(i, j) || isBiggerThanAllRight(i, j) || isBiggerThanAllAbove(i, j) || isBiggerThanAllBelow(i, j);
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


}