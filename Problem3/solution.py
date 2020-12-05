class solver:
    def __init__(self,area):
        self.area = area
        self.column = 0
        self.line = 0
        self.width = len(area[0])
        self.numberOfEncounteredTrees = 0
        self.stepsRight = 3
        self.stepsDown = 1
    def step(self):
        self.column += self.stepsRight
        self.column %= self.width
        self.line += self.stepsDown

    def getPosition(self):
        return self.area[self.line][self.column]

    def solve(self, stepsRight = 3, stepsDown = 1):
        self.stepsRight = stepsRight
        self.stepsDown = stepsDown
        try:
            while True:
                self.step()
                if self.getPosition() == '#':
                    self.numberOfEncounteredTrees += 1
        except IndexError:
            return self.numberOfEncounteredTrees

def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.abspath(__file__)
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath,'r') as file:
        result = parsePuzzleInputFrom(file)
    return result

def parsePuzzleInputFrom(file):
    result = []
    lines = file.readlines()
    for line in lines:
        result.append(line.strip('\n'))
    return result

if __name__ == '__main__':
    puzzleInput = parseFile( )
    print(solver(puzzleInput).solve(1,1))
    print(solver(puzzleInput).solve(3,1))
    print(solver(puzzleInput).solve(5,1))
    print(solver(puzzleInput).solve(7,1))
    print(solver(puzzleInput).solve(1,2))


