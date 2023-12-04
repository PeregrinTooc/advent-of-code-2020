const day = "2";
const path = require("node:path");
const solver = require("../../src/day" + day + "/solver");

test("should return correct result", () => {
  expect(
    solver.start1(path.resolve(path.join("tests", "day" + day, "input1.txt")))
  ).toBe(0);
});

test("should return correct result", () => {
  expect(
    solver.start2(path.resolve(path.join("tests", "day" + day, "input2.txt")))
  ).toBe(0);
});
