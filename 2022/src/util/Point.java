package util;

import java.util.Objects;

public abstract class Point {
    protected final int x;
    protected final int y;

    protected Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point create(Integer x, Integer y) {
        return new PointImpl(x, y);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof Point) {
            var other = (Point) obj;
            return other.x == this.x && other.y == this.y;
        } else
            return super.equals(obj);
    }

    public final int newYorkDistanceTo(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    @Override
    public final String toString() {
        return String.format("X:%s,Y:%s", x, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private static class PointImpl extends Point {
        public PointImpl(Integer x, Integer y) {
            super(x, y);
        }
    }
}
