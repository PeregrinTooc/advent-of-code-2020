package day12;

import java.util.ArrayList;

public class Solver {

    private final String[] map;
    private Point startPoint;
    private Point endPoint;

    public Solver(String[] input) {
        this.map = input;
    }

    public Map createMap() {

        int[][] heights = new int[map.length][map[0].length()];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < this.map[i].length(); j++) {
                convertToNumeric(heights, i, j);
            }
        }
        Map heightMap = Map.create(heights);
        if (startPoint != null) {
            heightMap.setStartPoint(startPoint);
        }
        if (endPoint != null) {
            heightMap.setEndPoint(endPoint);
        }
        return heightMap;
    }

    private void convertToNumeric(int[][] heights, int i, int j) {
        char c = map[i].charAt(j);
        if (c == 'S') {
            c = 'a';
            startPoint = new Point(j, i);
        }
        if (c == 'E') {
            c = 'z';
            endPoint = new Point(j, i);
        }
        heights[i][j] = Integer.valueOf(c) - 97;
    }

    public Integer solve1() {
        var heightMap = createMap();

        heightMap.calculateOptimalPath();
        return heightMap.findShortestWayLength();
    }

    public Integer solve2() {
        var heightMap = createMap();
        var pathLengths = new ArrayList<Integer>();
        var paths = new ArrayList<RouteSegment>();
        heightMap.calculateOptimalPath();
        pathLengths.add(heightMap.findShortestWayLength());
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                if (map[i].charAt(j) == 'a') {
                    heightMap.setStartPoint(new Point(j, i));
                    paths.add(heightMap.calculateOptimalPath());
                    pathLengths.add(heightMap.findShortestWayLength());
                }
            }
        }
        pathLengths.sort(null);
        return pathLengths.get(0);
    }
}