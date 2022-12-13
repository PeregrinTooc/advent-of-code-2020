package day10;

import util.Utils;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Solver {
    public static final int MAX_CYCLES = 241;
    private final String[] instructions;
    private final Integer[] times = new Integer[]{20, 60, 100, 140, 180, 220};
    private final int[] registerValues = new int[MAX_CYCLES];

    public Solver(String[] input) {
        this.instructions = input;
    }

    public static void main(String[] args) {
        URL path = allTests.class.getResource("input.txt");
        File realFile = new File(path.getFile());
        String[] input = Utils.transform(realFile);
        var solver = new Solver(input);
        solver.runProgram();
        solver.draw();
    }

    private void draw() {
        char[][] screen = new char[6][40];
        for (int i = 0; i < MAX_CYCLES - 1; i++) {
            screen[i / 40][i % 40] = getPixel(i);
        }
        for (char[] s : screen) {
            System.out.println(new String(s));
        }
    }

    private char getPixel(int i) {
        var spriteCenter = registerValues[i + 1];
        var currentPixelPosition = i % 40;
        if (currentPixelPosition == spriteCenter - 1 || currentPixelPosition == spriteCenter || currentPixelPosition == spriteCenter + 1) {
            return '#';
        }
        return '.';
    }

    public int solve1() {
        runProgram();
        AtomicInteger result = new AtomicInteger();
        Arrays.asList(times).forEach(i -> {
            result.addAndGet(i * registerValues[i]);
        });
        return result.get();
    }

    private void runProgram() {
        registerValues[1] = 1;
        var cycle = 1;
        for (var instruction : instructions) {
            if (instruction.equals("noop")) {
                cycle++;
                if (cycle == MAX_CYCLES) {
                    break;
                }
                registerValues[cycle] = registerValues[cycle - 1];
            } else {
                cycle++;
                if (cycle == MAX_CYCLES) {
                    break;
                }
                registerValues[cycle] = registerValues[cycle - 1];
                cycle++;
                if (cycle == MAX_CYCLES) {
                    break;
                }
                registerValues[cycle] = registerValues[cycle - 1] + Integer.valueOf(instruction.split(" ")[1]);
            }
        }
    }

    public int solve2() {
        return 0;
    }
}