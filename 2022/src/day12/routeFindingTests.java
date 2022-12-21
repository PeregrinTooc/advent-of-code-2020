package day12;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class routeFindingTests {
    private Map map;
    private RouteSegment initialSegment;
    private Point startPoint;
    private Point endPoint;

    @Test
    void routeFindingWithoutBarriersOnTinyMap() {
        initializeMap(new int[][]{{0, 0}, {0, 0}});

        endPoint = new Point(0, 0);
        assertDistanceAndOptimalPath(initialSegment, 0);
        assertHeuristic(new int[][]{{0, 1}, {1, 2}});

        endPoint = new Point(1, 0);
        assertDistanceAndOptimalPath(new RouteSegment(endPoint, initialSegment), 1);

        endPoint = new Point(0, 1);
        assertDistanceAndOptimalPath(new RouteSegment(endPoint, initialSegment), 1);

        endPoint = new Point(1, 1);
        RouteSegment routeSegment1 = new RouteSegment(endPoint, new RouteSegment(new Point(1, 0), initialSegment));
        RouteSegment routeSegment2 = new RouteSegment(endPoint, new RouteSegment(new Point(0, 1), initialSegment));
        assertDistanceAndOptimalPath(Arrays.asList(new RouteSegment[]{routeSegment1, routeSegment2}), 2);
        assertHeuristic(new int[][]{{2, 1}, {1, 0}});
    }

    @Test
    void routeFindingWithoutBarriersOnBiggerMap() {
        initializeMap(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        endPoint = new Point(2, 0);
        assertDistanceAndOptimalPath(new RouteSegment(endPoint, new RouteSegment(new Point(1, 0), initialSegment)), 2);
    }

    private void assertDistanceAndOptimalPath(RouteSegment routeSegment, int expectedDistance) {
        map.setEndPoint(endPoint);
        assertEquals(map.calculateOptimalPath(), routeSegment);
        int distance = map.findShortestWayLength();
        assertEquals(expectedDistance, distance);
    }

    private void assertDistanceAndOptimalPath(List<RouteSegment> possiblePaths, int expectedDistance) {
        map.setEndPoint(endPoint);
        RouteSegment optimalPath = map.calculateOptimalPath();
        assertTrue(possiblePaths.stream().map(route -> route.equals(optimalPath)).reduce(false, (b1, b2) -> b1 || b2));
        int distance = map.findShortestWayLength();
        assertEquals(expectedDistance, distance);
    }

    private void initializeMap(int[][] heights) {
        startPoint = new Point(0, 0);
        map = Map.create(heights);
        map.setStartPoint(startPoint);
        initialSegment = new RouteSegment(startPoint, null);
    }

    @Test
    void routeFindingWithBarriers() {
        initializeMap(new int[][]{{0, 1, 3}, {1, 0, 2}, {2, 1, 1}});

        endPoint = new Point(2, 0);
        map.setEndPoint(endPoint);
        assertHeuristic(new int[][]{{2, 1, 0}, {3, 2, 1}, {4, 3, 2}});
        map.calculateOptimalPath();
        assertEquals(6, map.findShortestWayLength());
        initializeMap(new int[][]{
                {0, 1, 2, 5, 4, 3, 2},
                {0, 1, 2, 6, 4, 3, 1},
                {1, 2, 3, 7, 6, 6, 2},
                {2, 3, 4, 5, 4, 4, 3}});
        map.setEndPoint(new Point(6, 0));
        map.calculateOptimalPath();
        assertEquals(12, map.findShortestWayLength());
    }

    private void assertHeuristic(int[][] distances) {
        Map heuristic = map.getHeuristics();
        assertEquals(heuristic, Map.create(distances));
    }

}