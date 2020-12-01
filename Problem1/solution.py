class solver:
    def __init__(self, numbers):
        self.numbers = numbers

    def getResultForTwo(self):
        for i in range(len(self.numbers)):
            for j in range(i,len(self.numbers)):
                if self.numbers[i]+self.numbers[j] == 2020:
                    return self.numbers[i]*self.numbers[j]
    
    def getResultForThree(self):
        for i in range(len(self.numbers)):
            for j in range(i,len(self.numbers)):
                for k in range(i,len(self.numbers)):
                    if self.numbers[i]+self.numbers[k]+self.numbers[j] == 2020:
                        return self.numbers[i]*self.numbers[j]*+self.numbers[k]

def getNumbersFromFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.abspath(__file__)
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath,'r') as file:
        result = file.readlines()
        for i in range(len(result)):
            result[i] = int(result[i][0:-1])
    return result

if __name__ == '__main__':
    numbers = getNumbersFromFile( )
    print(solver(numbers).getResultForThree())

