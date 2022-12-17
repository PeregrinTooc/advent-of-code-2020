package day12;

public class Solver {

    private final String[] map;

    public Solver(String[] input) {
        this.map = input;
    }

    public HeightMap createMap() {

        int[][] heights = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                convertToNumeric(heights, i, j);
            }
        }
        return HeightMap.create(heights);
    }

    private void convertToNumeric(int[][] heights, int i, int j) {
        char c = map[i].charAt(j);
        if (c == 'S') {
            c = 'a';
        }
        if (c == 'E') {
            c = 'z';
        }
        heights[i][j] = Integer.valueOf(c) - 97;
    }

    public Integer solve1() {
        return null;
    }

    public Integer solve2() {
        return null;
    }
}