package day1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.net.URL;

import util.Utils;

class Day1Test {
  static File file = null;
  static File realInput = null;
  Solver solver;

  @BeforeAll
  public static void classSetUp() {
    URL path = Day1Test.class.getResource("test.txt");
    file = new File(path.getFile());
    path = Day1Test.class.getResource("input.txt");
    realInput = new File(path.getFile());
  }

  @BeforeEach
  public void setUp() {
    // Add setup code here

  }

  @AfterEach
  public void tearDown() {
    // Add teardown code here

  }

  @Test
  public void acceptance() {
    String[] input = Utils.transform(file);
    assertEquals(24000l, new Solver(input).solve1());
    assertEquals(45000l, new Solver(input).solve2());
    input = Utils.transform(realInput);
    assertEquals(68802l, new Solver(input).solve1());
    assertEquals(205370l, new Solver(input).solve2());
  }

  @Test
  public void shouldReturnSumOfThreeElves() {
    solver = new Solver(new String[] { "1", "", "1", "", "1" });
    assertEquals(3, solver.solve2());
  }

  @Test
  public void shouldReturnMostCaloriesForManyElves() {
    solver = new Solver(new String[] { "1", "", "1", "2", "", "2" });
    assertEquals(3, solver.solve1());
  }

  @Test
  public void returnsCaloriesForTheOnlyElf() {
    solver = new Solver(new String[] { "1" });
    assertEquals(1, solver.solve1());
    solver = new Solver(new String[] { "1", "2" });
    assertEquals(3, solver.solve1());
  }

}
