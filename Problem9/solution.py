class solver:
    def __init__(self,puzzleInput):
        pass

    def solve(self):
        pass



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
    return None
    

if __name__ == '__main__':
    puzzleInput = parseFile( )
    print(solver(puzzleInput).solve())

