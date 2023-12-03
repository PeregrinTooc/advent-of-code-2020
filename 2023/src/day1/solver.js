const fs = require("node:fs");

function start1(path) {
  const input = fs.readFileSync(path, "utf8");
  return solve(input.split("\n"), firstAndLastNumberIn);
}

function start2(path) {
  const input = fs.readFileSync(path, "utf8");
  return solve(input.split("\n"), firstAndLastAfterReplace);
}

function firstAndLastAfterReplace(puzzleLine) {
  return firstAndLastNumberIn(
    puzzleLine
      .replaceAll("one", "one1one")
      .replaceAll("two", "two2two")
      .replaceAll("three", "three3three")
      .replaceAll("four", "four4four")
      .replaceAll("five", "five5five")
      .replaceAll("six", "six6six")
      .replaceAll("seven", "seven7seven")
      .replaceAll("eight", "eight8eight")
      .replaceAll("nine", "nine9nine")
  );
}

function solve(input, firstAndLastNumberIn) {
  const strings = input.map(firstAndLastNumberIn);
  return strings.reduce((n, s) => {
    return n + parseInt(s);
  }, 0);
}

function firstAndLastNumberIn(puzzleLine) {
  const inputWithoutLetters = puzzleLine.replace(/[a-z]/g, "").trim();

  return inputWithoutLetters.slice(0, 1) + inputWithoutLetters.slice(-1);
}

module.exports = { start1, start2, firstAndLastAfterReplace };
