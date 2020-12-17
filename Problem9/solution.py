import os


class NoKeyException(BaseException):
    pass


class solver:
    def __init__(self, puzzleInput, preambleLength):
        self.numbers = puzzleInput
        self.preambleLength = preambleLength

    def solve(self):
        line = self.findVulnerableLine()
        return self.numbers[line]

    def findVulnerableLine(self):
        line = 0
        try:
            for i in range(self.preambleLength + 1, len(self.numbers)):
                line = i
                self.findKeyForLine(line)
        except NoKeyException:
            pass
        return line

    def findKeyForLine(self, line):
        keys = self.numbers[line-self.preambleLength:line]
        for i in range(len(keys)):
            for j in range(i+1, len(keys)):
                candidate = [keys[i], keys[j]]
                if candidate[0] + candidate[1] == self.numbers[line] and candidate[0] != candidate[1]:
                    candidate.sort()
                    return candidate

        raise NoKeyException()

    def findContinuousSequenceAddingUpTo(self, number):
        pass


def parseFile():
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.abspath(__file__)
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath, 'r') as file:
        result = parsePuzzleInputFrom(file)
    return result


def parsePuzzleInputFrom(file):
    result = []
    for line in file.readlines():
        result.append(int(line.strip('\n')))
    return result


if __name__ == '__main__':
    puzzleInput = parseFile()
    print(solver(puzzleInput, 25).findVulnerableLine())
    print(solver(puzzleInput, 25).solve())
