package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {

    private final Valve startValve;

    public Solver(String[] input) {
        Valve.createNetwork(input);
        this.startValve = Valve.get("AA");
    }

    public int solve1() {
        var pressureReleaseCounter = new PressureReleaseCounter();
        startValve.traverse(pressureReleaseCounter);
        return pressureReleaseCounter.toInt();
    }

    public int solve2() {
        return 0;
    }

    private static class Valve {
        private static Map<String, Valve> valves = new HashMap<String, Valve>();
        private String id;
        private int flowRate;
        private List<Valve> targetValves = new ArrayList<>();
        private boolean open = false;

        public Valve(String id) {
            this.id = id;
            valves.put(id, this);
        }

        private static Valve create(String s) {
            var id = s.substring(6, 8);
            var valve = get(id);
            valve.flowRate = Integer.valueOf(s.substring(s.indexOf('=') + 1, s.indexOf(';')));
            var targetValveIds = s.substring(s.indexOf("valves ") + 7).split(", ");
            for (var targetValveId : targetValveIds) {
                valve.targetValves.add(get(targetValveId));
            }
            return valve;
        }

        public static void createNetwork(String[] input) {
            for (String valve : input) {
                var id = valve.substring(6, 8);
                new Valve(id);
            }
            for (String valve : input) {
                create(valve);
            }
        }

        public static Valve get(String id) {
            return valves.get(id);
        }

        public void traverse(PressureReleaseCounter pressureReleaseCounter) {
            if (open)
                return;
            open = true;
            pressureReleaseCounter.decreasePressure(flowRate);
            targetValves.get(0).traverse(pressureReleaseCounter);
        }
    }

    private class PressureReleaseCounter {

        private int minutes = 32;
        private int releasedPressure = 0;

        public void decreasePressure(int flowRate) {
            minutes -= 2;
            releasedPressure += minutes * flowRate;
        }

        public int toInt() {
            return releasedPressure;
        }
    }
}