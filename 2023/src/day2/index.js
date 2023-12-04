const path = require("node:path");
const solver = require("./solver");

const inputFilePath = path.resolve("input.txt");

console.log(solver.start1(inputFilePath));
console.log(solver.start2(inputFilePath));
