package day12;

import java.util.ArrayList;
import java.util.List;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            var other = (Point) obj;
            return other.x == this.x && other.y == this.y;
        } else return super.equals(obj);
    }

    public int distanceTo(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    public List<Point> getAdjacentWalkable(Map map) {
        List<Point> result = new ArrayList<>();
        Point p;
        p = new Point(x - 1, y);
        if (x > 0) {
            addAccessiblePointTo(result, map, p);
        }
        p = new Point(x, y - 1);
        if (y > 0) {
            addAccessiblePointTo(result, map, p);
        }
        p = new Point(x + 1, y);
        if (map.containsPointAt(p.x, p.y)) {
            addAccessiblePointTo(result, map, p);
        }
        p = new Point(x, y + 1);
        if (map.containsPointAt(p.x, p.y)) {
            addAccessiblePointTo(result, map, p);
        }
        return result;
    }

    private void addAccessiblePointTo(List<Point> result, Map map, Point p) {
        if (map.valueAt(p.x, p.y) <= map.valueAt(this.x, this.y) + 1) {
            result.add(p);
        }
    }

    public int bigger(Point other, Map heuristics) {
        if (this.equals(other)) {
            return 0;
        }
        if (heuristics.valueAt(x, y) > heuristics.valueAt(other.x, other.y)) {
            return 1;
        }
        return -1;
    }

    public int valueAt(Map map) {
        return map.valueAt(x, y);
    }
}