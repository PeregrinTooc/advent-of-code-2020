class solver:
    pass

class rule:
    def __init__(self) -> None:
        self.contained = []

    def getContainer(self):
        return self.container
    
    def setContainer(self,color):
        self.container = bag(color)

    def addContained(self,contained):
        self.contained.append(contained)

    def getContained(self):
        return self.contained

class bag:
    def __init__(self,color):
        self.color = color
    def getColor(self):
        return self.color

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
    for line in file.readlines():
        exit = False
        line = line.strip('\n')
        currentRule = rule()
        
        delimiter = line.find(' bags contain ')
        bagColor = line[:delimiter]
        currentRule.setContainer(bagColor) 
        if line.find('no other bags') > -1:
          exit = True  
        line = line[delimiter+len(' bags contain '):]
        while(not exit):
            delimiter = line.find(' ')
            count = line[:delimiter]
            line = line[delimiter+1:]
            delimiter = line.find(', ')
            if delimiter == -1:
                delimiter = line.find('.')
                exit = True
            bagDescription= line[:delimiter]
            line = line[delimiter+len(', '):]
            delimiter = bagDescription.find(' ')
            bagColor = bagDescription[:delimiter]
            bagDescription = bagDescription[delimiter+1:]
            delimiter = bagDescription.find(' ')
            bagColor =bagColor+' '+bagDescription[:delimiter]
            currentRule.addContained({'count' : int(count), 'bag' : bag(bagColor)})        
        result.append(currentRule)
    return result

if __name__ == '__main__':
    puzzleInput = parseFile( )
    print(solver(puzzleInput).solve())

