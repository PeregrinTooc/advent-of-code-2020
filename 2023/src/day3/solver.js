const fs = require("node:fs");

function start1(inputArray) {
  if (inputArray.length === 0) {
    return 0;
  } else {
    let result = 0;
    const coordinates = [];
    inputArray.forEach((line, i) => {
      for (let j = 0; j < line.length; j++) {
        const element = line[j];
        if (element === "*") {
          coordinates.push({ x: j, y: i });
        }
      }
    });
    coordinates.forEach(({ x, y }) => {
      if (x > 0) result += parseInt(inputArray[y].slice(0, x));
      result += parseInt(inputArray[y].slice(x + 1));
    });
    return result;
  }
}

function start2(inputArray) {
  return 0;
}

module.exports = { start1, start2 };
