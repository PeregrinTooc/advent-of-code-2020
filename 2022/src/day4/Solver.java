package day4;

public class Solver {

    private String[] areas;

    public Solver(String[] input) {
        this.areas = input;
    }

    public Integer solve1() {
        int result = 0;
        for (String area : areas) {
            String[] areaDemarcations = calculateAreaBorders(area);
            area area1 = new area(areaDemarcations[0], areaDemarcations[1]);
            area area2 = new area(areaDemarcations[2], areaDemarcations[3]);
            if (area1.contains(area2) || area2.contains(area1)) {
                result += 1;
            }
        }
        return result;
    }

    public Integer solve2() {
        int result = 0;
        for (String area : areas) {
            String[] areaDemarcations = calculateAreaBorders(area);
            area area1 = new area(areaDemarcations[0], areaDemarcations[1]);
            area area2 = new area(areaDemarcations[2], areaDemarcations[3]);
            if (area1.overlapsLeft(area2) || area2.overlapsLeft(area1)) {
                result += 1;
            }
        }
        return result;
    }

    private String[] calculateAreaBorders(String area) {
        String[] lowAndHigh = area.split(",");
        String[] result = new String[4];
        result[0] = lowAndHigh[0].split("-")[0];
        result[1] = lowAndHigh[0].split("-")[1];
        result[2] = lowAndHigh[1].split("-")[0];
        result[3] = lowAndHigh[1].split("-")[1];
        return result;
    }

    public class area {
        private int low;
        private int high;

        area(String low, String high) {
            this.low = Integer.valueOf(low);
            this.high = Integer.valueOf(high);
        }

        public boolean overlapsLeft(area area) {
            return low <= area.low && high >= area.low;
        }

        public boolean contains(area area) {
            return low <= area.low && high >= area.high;
        }
    }

}
