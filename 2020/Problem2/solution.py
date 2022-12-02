class abstractSolver:
    def setPasswords(self, passwords):
        self.passwords = passwords
    
    def solve(self):
        result = 0
        for password in self.passwords:
            if self.isValidPassword(password):
                result += 1 
        return result
class solver1(abstractSolver):
    def isValidPassword(self, passwordWithParameters):
        lower = passwordWithParameters[0]
        upper = passwordWithParameters[1]
        char = passwordWithParameters[2]
        password = passwordWithParameters[3]

        if password.count(char) < lower or password.count(char) > upper:
            return False
        else:
            return True

class solver2(abstractSolver):
    def isValidPassword(self, passwordWithParameters):
        lower = passwordWithParameters[0]
        upper = passwordWithParameters[1]
        char = passwordWithParameters[2]
        password = passwordWithParameters[3]

        if ( password[lower-1]==char ) ^ ( password[upper-1]==char ):
            return True
        else:
            return False

def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath,'r') as file:
        result = parsePuzzleInputFrom(file)
    return result

def parsePuzzleInputFrom(file):
    result = []
    input = file.readlines()
    for i in range(len(input)):
        result.append( parseLine(input[i].rstrip('\n')))
    return result

def parseLine(line):
    result = []
    result.append(int(line[0:line.find('-')]))
    line = line[line.find('-')+1:]
    result.append(int(line[0:line.find(' ')]))
    line = line[line.find(' ')+1:]
    result.append(line[0])
    result.append(line[3:])
    return result

if __name__ == '__main__':
    puzzleInput = parseFile( )
    solver = solver2()
    solver.setPasswords(puzzleInput)
    print(solver.solve())

