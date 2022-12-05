package day2;

public class Solver {

    private String[] strategy;

    public Solver(String[] strategy) {
        this.strategy = strategy;
    }

    public int solve1() {
        int result = 0;
        for (int i = strategy.length; i-- > 0;) {
            char[] currentRound = strategy[i].toCharArray();
            result += calculatePointsFor(currentRound);
        }
        return result;
    }

    private int calculatePointsFor(char[] currentRound) {
        int result = 0;
        int strategyAsInt = (int) currentRound[0] * (int) currentRound[2];
        result = getPoints(strategyAsInt);
        return result;
    }

    private int getPoints(int strategyAsInt) {
        int result = 0;
        switch (strategyAsInt) {
            case 65 * 88:
                result += 4;
                break;
            case 65 * 89:
                result += 8;
                break;
            case 65 * 90:
                result += 3;
                break;
            case 66 * 88:
                result += 1;
                break;
            case 66 * 89:
                result += 5;
                break;
            case 66 * 90:
                result += 9;
                break;
            case 67 * 88:
                result += 7;
                break;
            case 67 * 89:
                result += 2;
                break;
            case 67 * 90:
                result += 6;
                break;
        }
        return result;
    }

    public Integer solve2() {
        int result = 0;
        for (int i = strategy.length; i-- > 0;) {
            char[] correctMove = figureOutMove(strategy[i].toCharArray());
            result += calculatePointsFor(correctMove);
        }
        return result;
    }

    private char[] figureOutMove(char[] currentRound) {
        char[] result = new char[] { currentRound[0], currentRound[1], ' ' };
        int offset = 0;
        if (currentRound[2] == 'X') {
            offset = 0;
        }
        if (currentRound[2] == 'Y') {
            offset = 1;
        }
        if (currentRound[2] == 'Z') {
            offset = 2;
        }
        result[2] = (char) (((int) currentRound[0] + offset) % 3 + 88);
        return result;
    }

}
