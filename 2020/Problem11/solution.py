import copy


class solver:
    def __init__(self, grid=None, seats=None):
        if grid:
            self.grid = SeatingGrid(grid)
        if seats:
            self.grid = seats

    def solve(self):
        while not self.grid.isStable():
            self.grid.tick()
        return self.grid.getNumberOfOccupiedSeats()


class SeatingGrid:
    def __init__(self, grid):
        self.seats = []
        for row in grid:
            parsedRow = []
            for seat in row:
                parsedRow.append(Seat(seat))
            self.seats.append(parsedRow)
        self.stable = False

    def getNumberOfOccupiedSeats(self):
        result = 0
        for row in self.seats:
            for seat in row:
                if seat.isOccupied():
                    result += 1
        return result

    def getSeatAt(self, column, row):
        return self.seats[row][column]

    def tick(self):
        seats = copy.deepcopy(self.seats)
        for i in range(len(seats)):
            row = seats[i]
            for j in range(len(row)):
                seat = row[j]
                numberOfNeighbors = self.getNumberOfOccupiedNeighbors(i, j)
                if (numberOfNeighbors > 3 and seat.isOccupied()) or (numberOfNeighbors == 0 and not seat.isOccupied()):
                    seat.switchOccupied()
        self.checkStabilityWith(seats)
        self.seats = seats

    def checkStabilityWith(self, seats):
        self.stable = True
        for i in range(len(seats)):
            row = seats[i]
            for j in range(len(row)):
                seat = row[j]
                if not seat == self.seats[i][j]:
                    self.stable = False
                    return

    def isStable(self):
        return self.stable

    def getNumberOfOccupiedNeighbors(self, row, column):
        result = 0
        if row > 0 and column > 0 and self.seats[row-1][column-1].isOccupied():
            result += 1
        if row > 0 and self.seats[row-1][column].isOccupied():
            result += 1
        if row > 0 and column < len(self.seats[row-1])-1 and self.seats[row-1][column+1].isOccupied():
            result += 1
        if column > 0 and self.seats[row][column-1].isOccupied():
            result += 1
        if column < len(self.seats[row])-1 and self.seats[row][column+1].isOccupied():
            result += 1
        if row < len(self.seats)-1 and column > 0 and self.seats[row+1][column-1].isOccupied():
            result += 1
        if row < len(self.seats)-1 and self.seats[row+1][column].isOccupied():
            result += 1
        if row < len(self.seats)-1 and column < len(self.seats[row+1])-1 and self.seats[row+1][column+1].isOccupied():
            result += 1

        return result


class Seat:
    def __init__(self, seat, occupied=False):
        self.floor = seat == '.'
        self.occupied = occupied

    def __eq__(self, o: object) -> bool:
        return o.floor == self.floor and self.occupied == o.occupied

    def __copy__(self):
        if self.isFloor():
            seat = "."
        else:
            seat = 'L'
        return Seat(seat, self.occupied)

    def switchOccupied(self):
        if self.isFloor():
            return
        if self.occupied:
            self.occupied = False
        else:
            self.occupied = True

    def isOccupied(self):
        return self.occupied

    def isFloor(self):
        return self.floor


def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath, 'r') as file:
        result = parsePuzzleInputFrom(file)
    return result


def parsePuzzleInputFrom(file):
    grid = []
    for line in file.readlines():
        grid.append(line.strip('\n'))
    result = SeatingGrid(grid)
    return result


if __name__ == '__main__':
    puzzleInput = parseFile()
    print(solver(seats=puzzleInput).solve())
