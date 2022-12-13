package day9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.javatuples.Pair;

public class Solver {

    private List<Pair<Direction, Integer>> instructions;

    public Solver(String[] input) {
        instructions = new ArrayList<Pair<Direction, Integer>>();
        for (int i = 0; i < input.length; i++) {
            String[] s = input[i].split(" ");
            instructions.add(new Pair<Direction, Integer>(Direction.make(s[0].charAt(0)), Integer.valueOf(s[1])));
        }
    }

    public int solve1() {
        Solver.mapOfHeadAndTail map = this.new mapOfHeadAndTail();
        map.follow(instructions);
        return map.countTouchedSpots();
    }

    public class mapOfHeadAndTail {
        Point headPosition = makePoint(0, 0);
        Point tailPosition = makePoint(0, 0);

        private Point makePoint(int x, int y) {
            return Point.make(x, y);
        }

        Set<Point> tailPositions = new HashSet<Point>();

        public mapOfHeadAndTail() {
            tailPositions.add(tailPosition);
        }

        public int countTouchedSpots() {
            return tailPositions.size();
            // - instructions.size();
        }

        public void follow(List<Pair<Direction, Integer>> instructions) {
            for (int i = 0; i < instructions.size(); i++) {
                for (int j = 0; j < instructions.get(i).getValue1(); j++) {
                    moveHeadByOne(instructions.get(i).getValue0());
                }
            }
        }

        private void moveHeadByOne(Direction direction) {
            headPosition = headPosition.shift(direction);
            tailPosition = tailPosition.shift(headPosition);
            tailPositions.add(tailPosition);
        }

    }

}
