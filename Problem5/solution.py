import math


class seatParser:
    def __init__(self, code):
        self.row = code[:7]
        self.seat = code[7:]

    def getRow(self):
        result = 0
        for i in range(len(self.row)):
            if self.row[i] == 'F':
                result += 0
            else:
                result += 128/math.pow(2, i+1)
        return result

    def getSeat(self):
        result = 0
        for i in range(len(self.seat)):
            if self.seat[i] == 'L':
                result += 0
            else:
                result += 8/math.pow(2, i+1)
        return result

    def getSeatId(self):
        return self.getRow()*8+self.getSeat()


class solver:
    def __init__(self, seats):
        self.seats = seats

    def solve(self):
        result = 0
        for seat in self.seats:
            currentId = seatParser(seat).getSeatId()
            if currentId > result:
                result = currentId
        return result

    def findGap(self):
        result = 0
        seatIDs = []
        for seat in self.seats:
            seatIDs.append(seatParser(seat).getSeatId())
        seatIDs.sort()
        for i in (range(len(seatIDs))):
            if seatIDs[i+1] - seatIDs[i] == 2:
                return seatIDs[i]+1


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
        result.append(line.strip('\n'))
    return result


if __name__ == '__main__':
    puzzleInput = parseFile()
    print(solver(puzzleInput).solve())
    print(solver(puzzleInput).findGap())
