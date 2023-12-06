const fs = require("node:fs");
class Game {
  id;
  samples;
  matches({ red, green, blue }) {
    let result = true
    this.parseSamples();
    this.samples.forEach((sample) => {
      result &&= (sample.red <= red && sample.blue <= blue && sample.green <= green)
    })
    return result
  };
  parseSamples() {
    this.samples = this.samples.map((s => {
      let result = { blue: 0, red: 0, green: 0 };
      s.split(',').forEach((sample) => {
        sample = sample.trim(); const draws = sample.split(' '); for (let i = 1; i < draws.length; i++) {
          const colour = draws[i];
          switch (colour) {
            case 'red':
              result.red = parseInt(draws[i - 1]);
              break;
            case 'blue':
              result.blue = parseInt(draws[i - 1]);
              break;
            default:
              result.green = parseInt(draws[i - 1]);
              break;
          }
        }
      });
      return result;
    }));
  }

  power() {
    const reds = [], blues = [], greens = [];
    this.parseSamples();
    this.samples.forEach((sample) => {
      reds.push(sample.red);
      blues.push(sample.blue);
      greens.push(sample.green);
    });
    return reds.sort((a, b) => a - b).slice(-1) * greens.sort((a, b) => a - b).slice(-1) * blues.sort((a, b) => a - b).slice(-1)
  }
}

function parseGames(input) {
  return input.split('\n').map((line) => {
    let game = new Game();
    const parts = line.split(':');
    game.id = parseInt(parts[0].replace('Game ', ''));
    game.samples = parts[1].split(':')[0].split(';');
    return game;
  });
}

function start1(path) {
  const input = fs.readFileSync(path, "utf8");
  let result = 0;
  let games = parseGames(input);
  games.forEach((game) => {
    if (game.matches({ red: 12, green: 13, blue: 14 })) {
      result += game.id
    }
  })
  return result;
}



function start2(path) {
  const input = fs.readFileSync(path, "utf8");
  let games = parseGames(input);
  // return games[0].power() + games[1].power() + games[2].power() + games[3].power() + games[4].power();
  return games.reduce((n, game) => {
    return n + game.power();
  }, 0);
}

module.exports = { start1, start2 }
