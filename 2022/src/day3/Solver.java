package day3;

import java.util.HashSet;

public class Solver {

    private String[] rucksacks;

    public Solver(String[] input) {
        rucksacks = input;
    }

    public Integer solve1() {
        int result = 0;
        for (String rucksack : rucksacks) {
            result += getValueOf(rucksack);
        }
        return result;
    }

    private int getValueOf(String rucksack) {
        int helper = 0;
        HashSet<Character> firstHalf = new HashSet<Character>();
        HashSet<Character> secondHalf = new HashSet<Character>();
        for (int i = rucksack.length() / 2; i-- > 0;) {
            firstHalf.add(rucksack.substring(0, rucksack.length() / 2).toCharArray()[i]);
            secondHalf.add(rucksack.substring(rucksack.length() / 2).toCharArray()[i]);
        }
        for (Character item : firstHalf) {
            if (secondHalf.contains(item)) {
                helper = getPrio(item);
            }
        }
        return helper;
    }

    private Integer getPrio(char item) {
        int offset;
        if (Character.isUpperCase(item)) {
            offset = 38;
        } else {
            offset = 96;
        }
        return (int) item - offset;
    }

    public Integer solve2() {
        int result = 0;
        for (int i = 0; i < rucksacks.length; i += 3) {
            String[] groupRucksack = new String[] { rucksacks[i], rucksacks[i + 1], rucksacks[i + 2] };
            result += getPrioOfCommonItem(groupRucksack);
        }

        return result;
    }

    private int getPrioOfCommonItem(String[] groupRucksack) {
        int result = 0;
        HashSet<Character> firstRucksack = new HashSet<Character>();
        HashSet<Character> secondRucksack = new HashSet<Character>();
        HashSet<Character> thirdRucksack = new HashSet<Character>();
        for (int i = 0; i < groupRucksack[0].length(); i++)
            firstRucksack.add(groupRucksack[0].charAt(i));
        for (int i = 0; i < groupRucksack[1].length(); i++)
            secondRucksack.add(groupRucksack[1].charAt(i));
        for (int i = 0; i < groupRucksack[2].length(); i++)
            thirdRucksack.add(groupRucksack[2].charAt(i));
        for (Character item : firstRucksack) {
            if (secondRucksack.contains(item) && thirdRucksack.contains(item)) {
                result = getPrio(item);
            }
        }
        return result;
    }
}
