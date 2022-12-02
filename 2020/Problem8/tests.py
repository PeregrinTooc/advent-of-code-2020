import unittest
from solution import solver
from solution import parsePuzzleInputFrom
from solution import repeatInstructionException
from solution import codeFixer

def parseFile(filename = 'PuzzleInputTest.txt'):
    import os
    relPath = filename
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)
    with open(absFilePath, 'r') as file:
        result = parsePuzzleInputFrom(file)
    return result

class findCorruptInstruction(unittest.TestCase):
    def setUp(self):
        self.parsedInput = parseFile('PuzzleInputTest2.txt')    
        self.cut = codeFixer(self.parsedInput)

    def test_switchFirstInstruction(self):
        self.switch(1)
        self.assertEqual(self.cut.getCode()[0],'jmp +0') 

    def test_switchSecondInstruction(self):       
        self.switch(3)
        self.assertEqual(self.cut.getCode()[2],'nop +4') 
    
    def test_switchSecondInstructionFirstIsSwitchedBack(self):        
        self.switch(3)
        self.assertEqual(self.cut.getCode()[0],'nop +0') 
    
    def test_switchFourTimesNoChange(self):
        self.switch(4)
        self.assertEqual(self.cut.getCode(),self.parsedInput) 

    def test_changedCodeStillRaisesException(self):
        with self.assertRaises(repeatInstructionException):
            self.switch(3)
            slv = solver(self.cut.getCode())
            for _ in range(10):
                slv.tick()

    def test_correctChangeNoException(self):
        self.switch(8)
        slv = solver(self.cut.getCode())
        with self.assertRaises(IndexError):
            while True:
                slv.tick()
        self.assertEqual(slv.getAcc(),8)   
    
    def switch(self, times):
        for _ in range(times):
            self.cut.switch()
  

class getResults(unittest.TestCase):
    def setUp(self):
        self.parsedInput = parseFile()
        self.cut = solver(self.parsedInput)

    def test_initialValue(self):
        self.assertEqual(self.cut.getAcc(),0)
    
    def test_nopStep(self):
        self.tickTimes(1)
        self.assertEqual(self.cut.getAcc(),0)

    def tickTimes(self,n):    
        for _ in range(n):
            self.cut.tick()

    def test_accRaisedForAcc(self):
        self.tickTimes(2)
        self.assertEqual(self.cut.getAcc(),1)

    def test_jump(self):
        self.tickTimes(4)
        self.assertEqual(self.cut.getAcc(),2)
    
    def test_negativeJump(self):
        self.tickTimes(6)
        self.assertEqual(self.cut.getAcc(),-1)
    
    def test_tickAbortsOnRepetition(self):
        with self.assertRaises(repeatInstructionException):
            self.tickTimes(10)
        self.assertEqual(self.cut.getAcc(),-1)


    


class parsePuzzleInput(unittest.TestCase):
    def setUp(self) -> None:
        self.parsedInput = parseFile()

    def test_line1(self):
        self.assertEqual(self.parsedInput[0],"nop +0")
    
    def test_line2(self):
        self.assertEqual(self.parsedInput[1],"acc +1")

if __name__ == '__main__':
    unittest.main()
