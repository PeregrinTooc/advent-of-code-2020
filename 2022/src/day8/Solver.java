package day8;

public class Solver {

    private int[][] input;

    public Solver(String[] input) {
        this.input = new int[input.length][input.length];
        for (int i = 0; i < this.input.length; i++) {
            for (int j = 0; j < this.input[i].length; j++) {
                this.input[i][j] = Integer.valueOf(input[i].substring(j, j + 1));
            }
        }
    }

    public Integer solve1() {
        if (input.length <= 1) {
            return 1;
        }
        int result = 2 * input.length + 2 * (input.length - 2);
        result += input[1][1];
        return result;
    }
}
