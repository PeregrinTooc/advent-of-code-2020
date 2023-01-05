package day15;

import java.util.*;

public class Solver {

    private final String[] input;
    private Set<Point> map;

    public Solver(String[] input) {
        this.input = input;
        map = new HashSet<Point>();
    }

    public int solve1(int y) {
        createMapAtY(y);
        return map.size();
    }

    private void createMapAtY(int y) {
        for (String sensorReading : input) {
            addPointsAt(y, sensorReading);
        }
    }

    private void addPointsAt(int y, String sensorReading) {
        var words = sensorReading.split(" ");
        var sx = Integer.valueOf(words[2].substring(2, words[2].length() - 1));
        var sy = Integer.valueOf(words[3].substring(2, words[3].length() - 1));
        var bx = Integer.valueOf(words[8].substring(2, words[8].length() - 1));
        var by = Integer.valueOf(words[9].substring(2));
        map.addAll(new Point(sx, sy).createAllNearerOrAsNearAsWithYCoordinate(new Point(bx, by), y));
    }


    public long solve2(int max) {
        Map<Point, Integer> sensorsWithDistance = new HashMap<Point, Integer>();
        for (String sensorReading : input) {
            var words = sensorReading.split(" ");
            var sx = Integer.valueOf(words[2].substring(2, words[2].length() - 1));
            var sy = Integer.valueOf(words[3].substring(2, words[3].length() - 1));
            var bx = Integer.valueOf(words[8].substring(2, words[8].length() - 1));
            var by = Integer.valueOf(words[9].substring(2));
            Point sensor = new Point(sx, sy);
            sensorsWithDistance.put(sensor, sensor.newYorkDistanceTo(new Point(bx, by)));
        }
        for (Point point : sensorsWithDistance.keySet()) {
            for (Point p : point.createBallWithRadius(sensorsWithDistance.get(point) + 1, max)) {
                var pointFound = true;
                for (Point sensor : sensorsWithDistance.keySet()) {
                    if (p.newYorkDistanceTo(sensor) <= sensorsWithDistance.get(sensor)) {
                        pointFound = false;
                        break;
                    }
                }
                if (pointFound)
                    return p.tuningFrequency();
            }
        }
        return 0;
    }

    private class Point extends util.Point {
        public Point(Integer x, Integer y) {
            super(x, y);
        }

        public Collection<Point> createBallWithRadius(Integer r, int max) {
            int[] plusMinus = new int[]{-1, +1};
            Collection<Point> result = new HashSet<Point>();
            for (int i = 0; i <= r; i++) {
                for (var sign1 : plusMinus) {
                    for (var sign2 : plusMinus) {
                        int x1 = x + sign1 * i;
                        int y1 = y + sign2 * (r - i);
                        if (x1 >= 0 && y1 >= 0 && x1 <= max && y1 <= max)
                            result.add(new Point(x1, y1));
                    }
                }
            }
            return result;
        }

        public Collection<Point> createAllNearerOrAsNearAsWithYCoordinate(Point other, int y) {
            Collection<Point> result = new HashSet<Point>();
            int diff = this.newYorkDistanceTo(other);
            for (int x = this.x - diff + Math.abs(this.y - y); x <= this.x + diff - Math.abs(this.y - y); x++) {
                result.add(new Point(x, y));
            }
            result.remove(other);
            return result;
        }

        public long tuningFrequency() {
            return 4000000L * x + y;
        }
    }
}