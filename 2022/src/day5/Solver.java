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
        var stacks = parameters.stacks;
        for (var instruction : parameters.instructions) {
            for (int i = instruction[0]; i-- > 0;) {
                var crate = stacks.get(instruction[1] - 1).pop();
                stacks.get(instruction[2] - 1).push(crate);
            }
        }
        return findTopRow(stacks);
    }

    public Object solve2(Parameters parameters) {
        var stacks = parameters.stacks;
        for (var instruction : parameters.instructions) {
            Stack<Character> buffer = new Stack<Character>();
            for (int i = instruction[0]; i-- > 0;) {
                var crate = stacks.get(instruction[1] - 1).pop();
                buffer.push(crate);
            }
            for (int i = buffer.size(); i-- > 0;) {
                stacks.get(instruction[2] - 1).push(buffer.pop());
            }
        }
        return findTopRow(stacks);
    }

    private String findTopRow(List<Stack<Character>> stacks) {
        var result = "";
        for (var stack : stacks) {
            result += (stack.isEmpty() ? " " : stack.peek());
        }
        ;
        return result;
    }

    public Parameters transform() {
        var stacks = splitAt(input).get(0);
        var instructions = splitAt(input).get(1);
        return new Parameters(
                stacks.toArray(new String[] { "" }),
                instructions.toArray(new String[] { "" }));
    }

    public class Parameters {
        private List<Stack<Character>> stacks;
        private int[][] instructions;

        Parameters(String[] stacks, String[] instructions) {
            createStacks(stacks);
            createInstructions(instructions);
        }

        private void createInstructions(String[] instructions) {
            this.instructions = new int[instructions.length][3];
            if (instructions.length == 0) {
                return;
            }
            for (int i = 0; i < instructions.length; i++) {
                this.instructions[i] = new int[] {
                        Integer.valueOf(instructions[i].split(" ")[1]),
                        Integer.valueOf(instructions[i].split(" ")[3]),
                        Integer.valueOf(instructions[i].split(" ")[5]) };
            }
        }

        private void createStacks(String[] stacks) {
            initializeStacks(stacks);
            fillStacks(stacks);
        }

        private void fillStacks(String[] stacks) {
            for (int j = stacks.length - 1; j-- > 0;) {
                var row = stacks[j];
                for (int i = 0; i < this.stacks.size(); i++) {
                    Character crate = Character.valueOf(row.charAt(4 * i + 1));
                    if (crate.charValue() == ' ')
                        continue;
                    this.stacks.get(i)
                            .push(crate);
                }
            }
        }

        private void initializeStacks(String[] stacks) {
            this.stacks = new ArrayList<Stack<Character>>();
            int numberOfStacks = parseNumberOfStacks(stacks);
            for (int i = numberOfStacks; i-- > 0;) {
                this.stacks.add(new Stack<Character>());
            }
        }

        private int parseNumberOfStacks(String[] stacks) {
            var temp = stacks[stacks.length - 1].split(" ");
            int numberOfStacks = Integer.valueOf(temp[temp.length - 1]);
            return numberOfStacks;
        }
    }

}