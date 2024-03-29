class repeatInstructionException(Exception):
    pass


class codeFixer:
    def __init__(self, code):
        self.code = code
        self.switchedLine = 0

    def switch(self):
        line = self.switchedLine
        self.switchJmpAndNop(line)
        if line > 0:
            line -= 1
            self.switchJmpAndNop(line)

        self.switchedLine += 1

    def switchJmpAndNop(self, line):
        if self.code[line][0:3] == 'jmp':
            self.code[line] = self.code[line].replace('jmp', 'nop')
        elif self.code[line][0:3] == 'nop':
            self.code[line] = self.code[line].replace('nop', 'jmp')

    def getCode(self):
        return self.code


class solver:
    def __init__(self, code):
        self.acc = 0
        self.line = 0
        self.code = code

    def getAcc(self):
        return self.acc

    def tick(self):
        command = self.code[self.line]
        self.handleAlreadyProcessed(command)
        self.handleNopInstruction(command)
        self.handleJmpInstruction(command)
        self.handleAccInstruction(command)

    def handleAccInstruction(self, command):
        if command[:3] == 'acc':
            if command.find('+') == -1:
                self.acc += int(command[command.find('-'):])
            else:
                self.acc += int(command[command.find('+'):])
            self.line += 1

    def handleJmpInstruction(self, command):
        if command[:3] == 'jmp':
            if command.find('+') == -1:
                self.line += int(command[command.find('-'):])
            else:
                self.line += int(command[command.find('+'):])

    def handleNopInstruction(self, command):
        if command[:3] == 'nop':
            self.line += 1

    def handleAlreadyProcessed(self, command):
        if command.find('proc') > -1:
            raise repeatInstructionException()
        self.code[self.line] += 'proc'


def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.abspath(__file__)
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath, 'r') as file:
        result = parsePuzzleInputFrom(file)
    return result


def parsePuzzleInputFrom(file):
    result = []
    lines = file.readlines()
    for line in lines:
        result.append(line.strip('\n'))
    return result


def getChangedProgram():
    code = initialCode.copy()
    codeDebgugger = codeFixer(code)
    codeDebgugger.switchJmpAndNop(i)
    code = codeDebgugger.getCode()
    program = solver(code)
    return program


if __name__ == '__main__':
    initialCode = parseFile()
    program = solver(initialCode)
    for i in range(len(initialCode)):
        program = getChangedProgram()
        try:
            while True:
                program.tick()
        except IndexError:
            break
        except repeatInstructionException:
            continue

    print(program.getAcc())
