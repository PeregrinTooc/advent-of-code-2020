package day5;

import java.util.Stack;
import static util.Utils.splitAt;;

public class Solver {

    private String[] input;

    public Solver(String[] input) {
        this.input = input;
    }

    public Solver() {
    }

    public String solve1(Parameters parameters) {
        return "";
    }

    public Parameters transform() {
        var stacks = splitAt(input).get(0);
        var instructions = splitAt(input).get(0);
        return new Parameters(
                stacks.toArray(new String[] { "" }),
                instructions.toArray(new String[] { "" }));
    }

    public class Parameters {
        private Stack<Character>[] stacks;
        private int[][] instructions;

        Parameters(String[] stacks, String[] instructions) {

        }
    }
}