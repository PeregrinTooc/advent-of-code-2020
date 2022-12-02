from types import GetSetDescriptorType
from typing import List
import math


class solver:
    def __init__(self, puzzleInput) -> None:
        self.jolts = puzzleInput.copy()
        self.jolts.insert(0, 0)
        self.cache = {}

    def calculateDistribution(self):
        self.differences = []
        for i in range(1, len(self.jolts)):
            self.differences.append(self.jolts[i]-self.jolts[i-1])

        ones = self.differences.count(1)
        twos = self.differences.count(2)
        threes = 1 + self.differences.count(3)
        return [ones, twos, threes]

    def solve(self):
        distribution = self.calculateDistribution()
        return distribution[0]*distribution[2]

    def breakAtThrees(self):
        self.calculateDistribution()
        result = []
        lastIndex = -1
        for i in range(len(self.differences)):
            if self.differences[i] == 3:
                result.append(self.jolts[lastIndex+1:i+1])
                lastIndex = i
        result.append(self.jolts[lastIndex+1:])
        return result

    def getDifferences(self) -> List:
        return self.differences.copy()

    def getPossibleCombinations(self):
        possibilities = 1
        for distr in self.breakAtThrees():
            possibilities *= self.getWaysFromStartToEnd(len(distr))
        return possibilities

    def getWaysFromStartToEnd(self, n) -> int:
        try:
            result = self.cache[n]
        except KeyError:
            result = 1  # original list is always valid
            currentList = [i for i in range(n)]
            result += len(self.findValidSubsets(currentList))
            self.cache[n] = result
        return result

    def findValidSubsets(self, currentList):
        result = set([])
        for i in range(1, len(currentList)-1):
            candidate = currentList.copy()
            del candidate[i]
            if self.isValidList(candidate):
                result.add(str(candidate))
                result = result.union(self.findValidSubsets(candidate))
        return result

    def isValidList(self, candidate):
        for i in range(1, len(candidate)):
            if candidate[i]-candidate[i-1] > 3:
                return False
        return True


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
    solvr = solver(puzzleInput)
    print(solvr.solve())
    distr = solvr.breakAtThrees()
    possibilities = solvr.getPossibleCombinations()
    print(possibilities)
