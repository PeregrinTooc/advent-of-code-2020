package day12;

import java.util.Arrays;

public class HeightMap {
    private int[][] heights;
    private Point startPoint;
    private Point endPoint;

    public HeightMap(int[][] heights) {
        this.heights = heights;
    }

    public static HeightMap create(int[][] heights) {
        return new HeightMap(heights);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HeightMap) {
            HeightMap other = (HeightMap) obj;
            if (this.heights.length != other.heights.length) {
                return false;
            }
            for (int i = 0; i < heights.length; i++) {
                if (!Arrays.equals(heights[i], other.heights[i])) {
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
    }

    public int findShortestWay() {
        return startPoint.distanceTo(endPoint);
    }

    public HeightMap createHeuristic() {
        int[][] heuristicDistance = new int[heights.length][heights.length];
        for (int y = 0; y < heights.length; y++) {
            for (int x = 0; x < heights.length; x++) {
                heuristicDistance[y][x] = new Point(x, y).distanceTo(endPoint);
            }
        }

        return HeightMap.create(heuristicDistance);
    }

    public Route getOptimalPath() {
        if (endPoint.equals(startPoint))
            return new Route(endPoint, null);

        return new Route(endPoint, new Route(startPoint, null));
    }
}