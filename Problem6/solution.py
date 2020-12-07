class solver:
    def __init__(self,puzzleInput):
        self.groups = puzzleInput

    def solve(self):
        result = 0
        for group in self.groups:
            result += len(group)
        return result

def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.abspath(__file__)
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath,'r') as file:
        result = parsePuzzleInputFrom(file)
    return result

def parsePuzzleInputFromPart1(file):
    result = []
    puzzleInput = file.readlines()
    currentSet = set([])
    for line in puzzleInput:
        line = line.strip('\n')
        if len(line) == 0:
            result.append(currentSet)
            currentSet = set([])
        else:
          currentSet = currentSet.union(set(line))     

    result.append(currentSet)                
    return result

def parsePuzzleInputFromPart2(file):
    import string
    result = []
    puzzleInput = file.readlines()
    currentSet = set(string.ascii_lowercase)
    for line in puzzleInput:
        line = line.strip('\n')
        if len(line) == 0:
            result.append(currentSet)
            currentSet = set(string.ascii_lowercase)
        else:
          currentSet = currentSet.intersection(set(line))     

    result.append(currentSet)                
    return result


def solve():
    puzzleInput = parseFile( )
    print(solver(puzzleInput).solve())

if __name__ == '__main__':
    parsePuzzleInputFrom = parsePuzzleInputFromPart1
    solve()
    parsePuzzleInputFrom = parsePuzzleInputFromPart2
    solve()

