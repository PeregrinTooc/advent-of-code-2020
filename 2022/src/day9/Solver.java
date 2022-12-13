package day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {

    private List<Pair> instructions;

    public Solver(String[] input) {
        instructions = new ArrayList<Pair>();
        for (int i = 0; i < input.length; i++) {
            String[] s = input[i].split(" ");
            instructions.add(new Pair(Direction.make(s[0].charAt(0)), Integer.valueOf(s[1])));
        }
    }

    public int solve1() {
        Solver.mapOfHeadAndTail map = this.new mapOfHeadAndTail();
        map.follow(instructions);
        return map.countTouchedSpots();
    }

    public int solve2() {
        Solver.mapOfHeadAndTail map = this.new mapOfHeadAndTail(10);
        map.follow(instructions);
        return map.countTouchedSpots();
    }

    public class mapOfHeadAndTail {
        Point tailPosition = makePoint(0, 0);
        Set<Point> tailPositions = new HashSet<Point>();
        Point[] rope;

        public mapOfHeadAndTail() {
            this(2);
        }

        public mapOfHeadAndTail(int ropelength) {
            tailPositions.add(tailPosition);
            rope = new Point[ropelength];
            for (int i = 0; i < ropelength; i++) {
                rope[i] = makePoint(0, 0);
            }
        }

        private Point makePoint(int x, int y) {
            return Point.make(x, y);
        }

        public int countTouchedSpots() {
            return tailPositions.size();
        }

        public void follow(List<Pair> instructions) {
            for (int i = 0; i < instructions.size(); i++) {
                for (int j = 0; j < instructions.get(i).numberOfSteps; j++) {
                    moveHeadByOne(instructions.get(i).direction);
                }
            }
        }

        private void moveHeadByOne(Direction direction) {
            rope[rope.length - 1] = rope[rope.length - 1].shift(direction);
            for (int i = rope.length - 1; i > 0; i--) {
                rope[i - 1] = rope[i - 1].shift(rope[i]);
            }
            tailPositions.add(rope[0]);
        }

    }

}