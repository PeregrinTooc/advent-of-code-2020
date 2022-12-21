package day12;

import java.util.Arrays;
import java.util.List;

public class Map {
    private int[][] values;
    private Point startPoint;
    private Point endPoint;
    private Map heuristics;
    private RouteSegment currentSegment;

    public Map(int[][] heights) {
        this.values = heights;
    }

    public static Map create(int[][] heights) {
        return new Map(heights);
    }

    public boolean containsPointAt(int x, int y) {
        return values.length > y && values[y].length > x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Map) {
            Map other = (Map) obj;
            if (this.values.length != other.values.length) {
                return false;
            }
            for (int i = 0; i < values.length; i++) {
                if (!Arrays.equals(values[i], other.values[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void setStartPoint(Point point) {
        this.startPoint = point;
    }

    public void setEndPoint(Point point) {
        this.endPoint = point;
        int[][] heuristicDistance = new int[values.length][values[0].length];
        for (int y = 0; y < values.length; y++) {
            for (int x = 0; x < values[y].length; x++) {
                heuristicDistance[y][x] = new Point(x, y).distanceTo(endPoint);
            }
        }
        heuristics = Map.create(heuristicDistance);
    }

    public int findShortestWayLength() {
        return currentSegment.getLength();
    }

    public Map getHeuristics() {
        return heuristics;
    }

    public RouteSegment calculateOptimalPath() {
        currentSegment = new RouteSegment(startPoint, null);
        List<RouteSegment> openList = new OpenClosedList<RouteSegment>();
        List<RouteSegment> closedList = new OpenClosedList<RouteSegment>();

        if (endPoint.equals(startPoint)) {
            return currentSegment;
        }

        openList.add(currentSegment);
        while (!openList.isEmpty()) {
            openList.sort((segment1, segment2) -> segment1.bigger(segment2, heuristics));
            currentSegment = openList.get(0);
            if (currentSegment.endsWith(endPoint)) {
                return currentSegment;
            }
            openList.remove(0);
            closedList.add(currentSegment);

            List<Point> adjacentWalkables = currentSegment.getAdjacentWalkable(this);
            RouteSegment finalCurrentSegment = currentSegment;
            adjacentWalkables.forEach(point -> {
                RouteSegment newSegment = new RouteSegment(point, finalCurrentSegment);
                if (!closedList.contains(newSegment))
                    if (openList.contains(newSegment)) {
                        var i = openList.indexOf(newSegment);
                        if (openList.get(i).getLength() > newSegment.getLength()) {
                            openList.remove(i);
                            openList.add(newSegment);
                        }
                        ;
                    } else {
                        openList.add(newSegment);
                    }
            });
        }
        currentSegment = new RouteSegment(endPoint, currentSegment);
        return currentSegment;

    }


    public int valueAt(int x, int y) {
        return this.values[y][x];
    }

    public int valueAt(Point point) {
        return point.valueAt(this);
    }
}