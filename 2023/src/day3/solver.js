const fs = require("node:fs");

function start1(path) {
  const input = fs.readFileSync(path, "utf8");
  return 0;
}

function start2(path) {
  const input = fs.readFileSync(path, "utf8");
  return 0;
}

module.exports = { start1, start2 }
