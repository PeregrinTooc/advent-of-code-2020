package day9;

import java.util.HashMap;

class Point {
    private int x;
    private int y;
    private static HashMap<String, Point> points = new HashMap<String, Point>();

    public static Point make(int x, int y) {
        String hash = x + "x" + y + "y";
        if (points.containsKey(hash)) {
            return points.get(hash);
        }
        Point result = new Point();
        result.x = x;
        result.y = y;
        points.put(hash, result);
        return result;
    }

    public Point shift(Direction direction) {
        int newX = x;
        int newY = y;
        if (direction == Direction.RIGHT) {
            newX++;
        }
        if (direction == Direction.LEFT) {
            newX--;
        }
        if (direction == Direction.UP) {
            newY++;
        }
        if (direction == Direction.DOWN) {
            newY--;
        }
        return make(newX, newY);
    }

    public Point shift(Point other) {
        if (this.isAdjacentTo(other)) {
            return this;
        }
        if (this.isStrictBelow(other)) {
            return this.shift(Direction.UP);
        }
        if (this.isStrictLeftOf(other)) {
            return this.shift(Direction.RIGHT);
        }
        if (this.isStrictAbove(other)) {
            return this.shift(Direction.DOWN);
        }
        if (this.isStrictRightOf(other)) {
            return this.shift(Direction.RIGHT);
        }
        if (this.isLeftBelow(other)) {
            return this.shift(Direction.UP).shift(Direction.RIGHT);
        }
        if (this.isLRightBelow(other)) {
            return this.shift(Direction.UP).shift(Direction.LEFT);
        }
        if (this.isLeftAbove(other)) {
            return this.shift(Direction.DOWN).shift(Direction.RIGHT);
        }
        if (this.isLRightAbove(other)) {
            return this.shift(Direction.DOWN).shift(Direction.LEFT);
        }
        return null;
    }

    private boolean isLRightAbove(Point other) {
        return this.y > other.y && this.x > other.x;
    }

    private boolean isLeftAbove(Point other) {
        return this.y > other.y && this.x < other.x;
    }

    private boolean isLRightBelow(Point other) {
        return this.y < other.y && this.x > other.x;
    }

    private boolean isLeftBelow(Point other) {
        return this.y < other.y && this.x < other.x;
    }

    private boolean isStrictRightOf(Point other) {
        return this.x > other.x && this.y == other.y;
    }

    private boolean isStrictAbove(Point other) {
        return this.y > other.y && this.x == other.x;
    }

    private boolean isStrictLeftOf(Point other) {
        return this.x < other.x && this.y == other.y;
    }

    private boolean isStrictBelow(Point other) {
        return this.y < other.y && this.x == other.x;
    }

    private boolean isAdjacentTo(Point headPosition) {
        return (this.x - headPosition.x <= 1)
                && (this.x - headPosition.x >= -1)
                && (this.y - headPosition.y <= 1)
                && (this.y - headPosition.y >= -1);
    }
}