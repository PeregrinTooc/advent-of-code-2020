package day11;

public class MonkeyBusiness {

    private Monkey[] monkeys;

    public boolean equals(MonkeyBusiness other) {
        if (this.monkeys.length != other.monkeys.length) {
            return false;
        }
        for (int i = 0; i < monkeys.length; i++) {
            if (!monkeys[i].equals(other.monkeys[i])) {
                return false;
            }
        }
        return true;
    }

    public void tick() {
        for (var monkey : monkeys) {
            monkey.tick();
        }
    }

    public static MonkeyBusiness createFromMonkeys(Monkey[] monkeys) {
        MonkeyBusiness result = new MonkeyBusiness();
        result.monkeys = monkeys;
        return result;

    }

}
