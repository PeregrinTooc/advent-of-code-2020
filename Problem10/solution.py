from types import GetSetDescriptorType


class solver:
    def __init__(self, puzzleInput) -> None:
        self.jolts = puzzleInput
        puzzleInput.insert(0, 0)

    def getDistribution(self):
        differences = []
        for i in range(1, len(self.jolts)):
            differences.append(self.jolts[i]-self.jolts[i-1])

        ones = differences.count(1)
        twos = differences.count(2)
        threes = 1 + differences.count(3)
        return [ones, twos, threes]

    def solve(self):
        distribution = self.getDistribution()
        return distribution[0]*distribution[2]


def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath, 'r') as file:
        result = parsePuzzleInputFrom(file)
    return result


def parsePuzzleInputFrom(file):
    result = []
    for line in file.readlines():
        result.append(int(line.strip('\n')))
    result.sort()

    return result


if __name__ == '__main__':
    puzzleInput = parseFile()
    print(solver(puzzleInput).solve())
