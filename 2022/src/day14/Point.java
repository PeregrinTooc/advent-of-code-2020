package day14;

import java.util.ArrayList;

public class Point extends util.Point {

    public Point(int x, int y) {
        super(x, y);
    }

    public static ArrayList<Point> createAllBetween(Point p, Point q) {
        ArrayList<Point> result = new ArrayList<>();
        result.add(p);
        result.add(q);
        if (p.x != q.x) {
            if (p.x < q.x) {
                for (int x = p.x + 1; x < q.x; x++) {
                    result.add(new Point(x, p.y));
                }
            } else {
                for (int x = q.x + 1; x < p.x; x++) {
                    result.add(new Point(x, q.y));
                }
            }
        } else {
            if (p.y < q.y) {
                for (int y = p.y + 1; y < q.y; y++) {
                    result.add(new Point(p.x, y));
                }
            } else {
                for (int y = q.y + 1; y < p.y; y++) {
                    result.add(new Point(q.x, y));
                }
            }
        }
        return result;
    }
}
