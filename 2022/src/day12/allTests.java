package day12;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import util.Utils;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class allTests {
    static File testFile = null;
    static File realFile = null;
    Solver solver = null;
    private HeightMap map;

    @BeforeAll
    static void classSetUp() {
        URL path = allTests.class.getResource("test.txt");
        testFile = new File(path.getFile());
        path = allTests.class.getResource("input.txt");
        realFile = new File(path.getFile());
    }

    @NotNull
    private static HeightMap getHeightMap(String[] input) {
        var solver = new Solver(input);
        HeightMap actual = solver.createMap();
        return actual;
    }

    private static void createAndAssertEquals(String[] input, int[][] heights) {
        var simpleMapWithoutStart = getHeightMap(input);
        var heightMap = HeightMap.create(heights);
        assertEquals(simpleMapWithoutStart, heightMap);
    }

    private void assertDistance(int expectedDistance, Point endPoint) {
        map.setEndPoint(endPoint);
        int distance = map.findShortestWay();
        assertEquals(expectedDistance, distance);
    }

    @Test
    @Disabled
    void acceptance() {
        String[] input = Utils.transform(testFile);
        assertEquals(2, new Solver(input).solve1());
        assertEquals(4, new Solver(input).solve2());
        input = Utils.transform(realFile);
        assertEquals(498, new Solver(input).solve1());
        assertEquals(859, new Solver(input).solve2());
    }

    @Test
    void mapCreation() {
        createAndAssertEquals(new String[]{"a"}, new int[][]{{0}});
        createAndAssertEquals(new String[]{"S"}, new int[][]{{0}});
        createAndAssertEquals(new String[]{"E"}, new int[][]{{25}});
        createAndAssertEquals(
                new String[]{
                        "Sbcdef",
                        "ghijkl",
                        "mnopqr",
                        "stuvwx",
                        "yEaaaa",
                        "xwvaba"},
                new int[][]{
                        {0, 1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10, 11},
                        {12, 13, 14, 15, 16, 17},
                        {18, 19, 20, 21, 22, 23},
                        {24, 25, 0, 0, 0, 0},
                        {23, 22, 21, 0, 1, 0}});
    }

    @Test
    void routeFindingWithoutBarriers() {
        map = HeightMap.create(new int[][]{{0, 1}, {1, 0}});
        Point startPoint = new Point(0, 0);
        map.setStartPoint(startPoint);

        Point endPoint = new Point(0, 0);
        assertDistance(0, endPoint);
        assertHeuristic(new int[][]{{0, 1}, {1, 2}});
        Route routeToOrigin = new Route(startPoint, null);
        assertEquals(map.getOptimalPath(), routeToOrigin);

        endPoint = new Point(1, 0);
        assertDistance(1, endPoint);
        assertEquals(map.getOptimalPath(), new Route(endPoint, routeToOrigin));
        assertDistance(1, new Point(0, 1));

        endPoint = new Point(1, 1);
        assertDistance(2, endPoint);
        Route route1 = new Route(endPoint, new Route(new Point(1, 0), routeToOrigin));
        Route route2 = new Route(endPoint, new Route(new Point(0, 1), routeToOrigin));
        assertTrue(route1.equals(map.getOptimalPath()) || route2.equals(map.getOptimalPath()));
    }

    @Test
    void routeFindingWithBarriers() {
        map = HeightMap.create(new int[][]{{0, 1, 3}, {1, 0, 2}, {2, 1, 1}});
        map.setStartPoint(new Point(0, 0));
        Point endPoint = new Point(2, 0);
        map.setEndPoint(endPoint);
        assertHeuristic(new int[][]{{2, 1, 0}, {3, 2, 1}, {4, 3, 2}});

        //assertDistance(4, endPoint);
    }

    private void assertHeuristic(int[][] distances) {
        HeightMap heuristic = map.createHeuristic();
        assertEquals(heuristic, HeightMap.create(distances));
    }

}