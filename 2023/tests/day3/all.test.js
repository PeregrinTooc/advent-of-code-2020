const day = "3";
const path = require("node:path");
const solver = require("../../src/day3/solver");
const fs = require("node:fs");

const input = [];

beforeEach(() => {
  emptyInput();
});

function emptyInput() {
  let popped = undefined;
  do {
    popped = input.pop();
  } while (popped !== undefined);
}

test("should return correct result", () => {
  return;
  expect(
    solver.start1(
      fs
        .readFileSync(
          path.resolve(path.join("tests", "day3", "input1.txt")),
          "utf8"
        )
        .split("\n")
    )
  ).toBe(4361);
});

test("should return 0 for empty array", () => {
  expect(solver.start1(input)).toBe(0);
});
test("should return the number for array with 1 number next to symbol", () => {
  given(["*123"]);
  expect(solver.start1(input)).toBe(123);
});

test("should return the number for array with 1 number next to symbol and another number without symbol", () => {
  given(["*123....456"]);
  expect(solver.start1(input)).toBe(123);
});

test("...even if it is in the next line", () => {
  given(["*123..", "..456."]);
  expect(solver.start1(input)).toBe(123);
});

test("should return the sum of numbers for array with 2 numbers next to symbol", () => {
  given(["1*2"]);
  expect(solver.start1(input)).toBe(3);
});

test("should count each number only once", () => {
  given(["*1*"]);
  expect(solver.start1(input)).toBe(1);
});

function given(inputArray) {
  inputArray.forEach((value) => {
    input.push(value);
  });
}

test("should return correct result", () => {
  expect(
    solver.start2(
      fs
        .readFileSync(
          path.resolve(path.join("tests", "day3", "input2.txt")),
          "utf8"
        )
        .split("\n")
    )
  ).toBe(0);
});
