const path = require("node:path");
const solver = require("../../src/day1/solver");

test("should return correct result", () => {
  expect(
    solver.start1(path.resolve(path.join("tests", "day1", "input1.txt")))
  ).toBe(142);
});

test("should return correct result", () => {
  expect(
    solver.start2(path.resolve(path.join("tests", "day1", "input2.txt")))
  ).toBe(281);
});

describe("replace words by numbers and return the first and last", () => {
  it.each([
    ["kffeightwogzcqpzdbhfvmckxmbhrgvonevcshkbctbc524", 84],
    ["khrjxxrltbpngsmzndgsjmzvgqxfhvkct6eightzrvpmpcc3", 63],
    ["756ghcxrmrgtninefivegvmtjhfrj", 75],
    ["one", 11],
    ["two", 22],
    ["oneightwo", 12],
    ["1onethreeoneighthree", 13],
  ])("when the input is '%s'", (text, expected) => {
    expect(parseInt(solver.firstAndLastAfterReplace(text))).toBe(expected);
  });
});
