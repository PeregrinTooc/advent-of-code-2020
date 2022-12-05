package day5;

import java.util.ArrayList;
import java.util.List;
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
        var result = "";
        for (var stack : parameters.stacks) {
            result += stack.peek();
        }
        ;
        return result;
    }

    public Parameters transform() {
        var stacks = splitAt(input).get(0);
        var instructions = splitAt(input).get(0);
        return new Parameters(
                stacks.toArray(new String[] { "" }),
                instructions.toArray(new String[] { "" }));
    }

    public class Parameters {
        private List<Stack<Character>> stacks;
        private int[][] instructions;

        Parameters(String[] stacks, String[] instructions) {
            this.stacks = new ArrayList<Stack<Character>>();
            Stack<Character> firstStack = new Stack<Character>();
            this.stacks.add(firstStack);
            Stack<Character> secondStack = new Stack<Character>();
            this.stacks.add(secondStack);
            Stack<Character> thirdStack = new Stack<Character>();
            this.stacks.add(thirdStack);
            firstStack.push(Character.valueOf('Z'));
            secondStack.push(Character.valueOf('M'));
            thirdStack.push(Character.valueOf('P'));
        }
    }
}