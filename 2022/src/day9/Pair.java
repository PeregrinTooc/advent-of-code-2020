package day9;

public class Pair {
    public int numberOfSteps;
    public Direction direction;

    public Pair(Direction direction, Integer numberOfSteps) {
        this.direction = direction;
        this.numberOfSteps = numberOfSteps;
    }
}