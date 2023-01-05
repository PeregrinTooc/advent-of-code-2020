package day14;

import java.util.Collection;
import java.util.HashSet;

public class Solver {

    private final Collection<Point> map;
    private int numberOfSettledUnitsOfSand;
    private int highY;

    public Solver(String[] input) {
        highY = 0;
        numberOfSettledUnitsOfSand = 0;
        map = new HashSet<Point>();
        prepareMap(input);
    }

    private void prepareMap(String[] input) {
        for (String path : input) {
            drawOnMap(path);
        }
    }

    private void drawOnMap(String path) {
        final var points = path.split(" -> ");
        for (int i = 0; i < points.length - 1; i++) {
            final var coordinates1 = points[i].split(",");
            final var coordinates2 = points[i + 1].split(",");
            createPointsAndDrawOnMap(coordinates1, coordinates2);
        }
    }

    private void createPointsAndDrawOnMap(String[] coordinates1, String[] coordinates2) {
        Integer y1 = Integer.valueOf(coordinates1[1]);
        Integer y2 = Integer.valueOf(coordinates2[1]);
        highY = y1 > highY ? y1 : highY;
        highY = y2 > highY ? y2 : highY;
        map.addAll(Point.createAllBetween(
                new Point(Integer.valueOf(coordinates1[0]), y1),
                new Point(Integer.valueOf(coordinates2[0]), y2)));
    }

    public int solve1() {
        var settledBefore = -1;
        while (settledBefore != numberOfSettledUnitsOfSand) {
            settledBefore = numberOfSettledUnitsOfSand;
            tick();
        }
        return numberOfSettledUnitsOfSand;
    }

    public int solve2() {
        highY += 2;
        map.addAll(Point.createAllBetween(
                new Point(0, highY),
                new Point(1000, highY)));
        Point origin = new Point(500, 0);
        while (!map.contains(origin)) {
            tick();
        }
        return numberOfSettledUnitsOfSand;
    }

    public boolean isOccupied(Point point) {
        return map.contains(point);
    }

    private void tick() {
        int x = 500;
        int y = 0;
        while (y < highY) {
            if (belowIsOccupied(x, y)) {
                y++;
                continue;
            }
            if (belowLeftIsFree(x, y)) {
                y++;
                x--;
                continue;
            }
            if (belowRightIsFree(x, y)) {
                y++;
                x++;
                continue;
            }
            map.add(new Point(x, y));
            numberOfSettledUnitsOfSand++;
            break;
        }
    }

    private boolean belowRightIsFree(int x, int y) {
        return !map.contains(new Point(x + 1, y + 1));
    }

    private boolean belowLeftIsFree(int x, int y) {
        return !map.contains(new Point(x - 1, y + 1));
    }

    private boolean belowIsOccupied(int x, int y) {
        return !map.contains(new Point(x, y + 1));
    }


}