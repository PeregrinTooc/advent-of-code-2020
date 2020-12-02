class solver:
    pass

def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath,'r') as file:
        result = parsePuzzleInputFrom(file)
    return result

def parsePuzzleInputFrom(file):
    pass

if __name__ == '__main__':
    puzzleInput = parseFile( )
    print(solver(puzzleInput).getResultForThree())

