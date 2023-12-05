const fs = require("node:fs");

function start1(path) {
  let result = 0;
  const predicate = { red: 12, green: 13, blue: 14 }
  const input = fs.readFileSync(path, "utf8");
  let games = input.split('\n').map((line) => {
    let game = {};
    const parts = line.split(':');
    game.id = parseInt(parts[0].slice(-1)); game.samples = parts[1].split(':');
    return game
  });
  games.forEach((game) => {
    if (game.id === 1 || game.id === 2 || game.id === 5) {
      result += game.id
    }
  })
  return result;
}

function start2(path) {
  const input = fs.readFileSync(path, "utf8");
  return 0;
}

module.exports = { start1, start2 }
