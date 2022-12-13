package day9;

class Direction {
    public Direction(char c) {
    }

    public static final Direction RIGHT = new Direction('R');
    public static final Direction LEFT = new Direction('L');
    public static final Direction UP = new Direction('U');
    public static final Direction DOWN = new Direction('D');

    public static Direction make(char direction) {
        switch (direction) {
            case 'R':
                return RIGHT;
            case 'L':
                return LEFT;
            case 'U':
                return UP;
            default:
                return DOWN;
        }
    }
}