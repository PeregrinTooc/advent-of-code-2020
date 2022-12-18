package day12;

public class Route {
    private final Point point;
    private final Route parent;

    public Route(Point point, Route parent) {
        this.point = point;
        this.parent = parent;


    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Route) {
            return pointsAreEqual((Route) obj) && originEquals((Route) obj);
        }
        return false;
    }

    private boolean originEquals(Route other) {
        return this.parent == null ? other.parent == null : this.parent.equals(other.parent);
    }

    private boolean pointsAreEqual(Route other) {
        return this.point.equals(other.point);
    }
}
