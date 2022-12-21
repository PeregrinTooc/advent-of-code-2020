package day12;

import java.util.List;

public class RouteSegment {
    private final Point point;
    private final RouteSegment parent;
    private final Point origin;
    private int length;

    public RouteSegment(Point point, RouteSegment parent) {
        this.point = point;
        this.parent = parent;
        if (this.parent == null) {
            length = 0;
            this.origin = point;
        } else {
            length = parent.getLength() + 1;
            this.origin = parent.origin;
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RouteSegment) {
            return pointsAreEqual((RouteSegment) obj) && originEquals((RouteSegment) obj);
        }
        return false;
    }

    private boolean originEquals(RouteSegment other) {
        return this.parent == null ? other.parent == null : this.parent.equals(other.parent);
    }

    private boolean pointsAreEqual(RouteSegment other) {
        return this.point.equals(other.point);
    }

    public boolean endsWith(Point endPoint) {
        return this.point.equals(endPoint);
    }

    public int getLength() {
        return length;
    }

    public int bigger(RouteSegment segment2, Map heuristics) {
        var thisF = heuristics.valueAt(point) + getLength();
        var otherF = heuristics.valueAt(segment2.point) + segment2.getLength();

        if (thisF == otherF) {
            return 0;
        }
        if (thisF > otherF) {
            return 1;
        }
        return -1;
    }

    public boolean hasSameEndpointAs(Object o) {
        if (o instanceof RouteSegment) {
            var other = (RouteSegment) o;
            return this.point.equals(other.point);
        }
        return false;
    }

    public List<Point> getAdjacentWalkable(Map map) {
        return this.point.getAdjacentWalkable(map);
    }
}
