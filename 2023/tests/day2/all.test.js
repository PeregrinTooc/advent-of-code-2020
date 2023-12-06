const path = require("node:path");
const solver = require("../../src/day2/solver");

test("should return correct result", () => {
  expect(
    solver.start1(path.resolve(path.join("tests", "day2", "input1.txt")))
  ).toBe(1 + 2 + 5);
});

test("should return correct result", () => {
  expect(
    solver.start2(path.resolve(path.join("tests", "day2", "input1.txt")))
  ).toBe(4 * 2 * 6 + 1 * 3 * 4 + 20 * 13 * 6 + 14 * 3 * 15 + 6 * 3 * 2);
});
