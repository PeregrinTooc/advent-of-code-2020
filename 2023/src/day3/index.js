const path = require("node:path");
const solver = require("./solver");
const fs = require("node:fs");

const inputString = fs.readFileSync(path.resolve("input.txt"), "utf8");
const input = inputString.split("\n");

console.log(solver.start1(input));
console.log(solver.start2(input));
