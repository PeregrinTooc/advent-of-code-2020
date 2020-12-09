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
        noMoreBags, line, currentRule = initialize(line)
        delimiter = createRuleAndGetDelimiter(line, currentRule) 
        noMoreBags = thereAreNoBagsContained(line, noMoreBags)  
        line = line[delimiter+len(' bags contain '):]
        while(not noMoreBags):
            line, count = getCount(line)
            delimiter, noMoreBags = getDelimiterForContainedBags(line, delimiter)
            bagColor, line = getBagColor(line, delimiter)
            currentRule.addContained({'count' : int(count), 'bag' : bag(bagColor)})        
        result.append(currentRule)
    return result

def getBagColor(line, delimiter):
    bagDescription= line[:delimiter]
    line = line[delimiter+len(', '):]
    bagDescription, bagColor = getCount(bagDescription)
    delimiter = bagDescription.find(' ')
    bagColor =bagColor+' '+bagDescription[:delimiter]
    return bagColor, line

def thereAreNoBagsContained(line, result):
    if line.find('no other bags') > -1:
      result = True  
    return result

def getDelimiterForContainedBags(line, delimiter):
    exit = False
    delimiter = line.find(', ')
    if delimiter == -1:
        delimiter = line.find('.')
        exit = True
    return delimiter, exit

def getCount(line):
    delimiter = line.find(' ')
    count = line[:delimiter]
    line = line[delimiter+1:]
    return line, count

def createRuleAndGetDelimiter(line, currentRule):
    delimiter = line.find(' bags contain ')
    bagColor = line[:delimiter]
    currentRule.setContainer(bagColor) 
    return delimiter

def initialize(line):
    exit = False
    line = line.strip('\n')
    currentRule = rule()
    return exit, line, currentRule

if __name__ == '__main__':
    puzzleInput = parseFile( )
    print(solver(puzzleInput).solve())

