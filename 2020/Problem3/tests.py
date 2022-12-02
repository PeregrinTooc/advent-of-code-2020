import unittest
from solution import solver
from solution import parsePuzzleInputFrom

def getFile():
    import os
    relPath = 'PuzzleInputTest.txt'
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)
    return open(absFilePath, 'r')


class parseFile(unittest.TestCase):
    def setUp(self):
        self.file = getFile()
        self.fileLength = 20

    def runCut(self):
        return parsePuzzleInputFrom(self.file)
    
    def tearDown(self):
        self.file.close()

    def test_result_length(self):
        self.assertEqual(len(self.runCut()),self.fileLength) 

    def test_format(self):
        for line in self.runCut():
            for char in line:
                self.assertTrue((char == '.' ) ^ ( char == '#' ))

class solve(unittest.TestCase):
    def setUp(self):
        self.file = getFile()
        self.area = parsePuzzleInputFrom(self.file)
        self.cut = solver(self.area)
    
    def tearDown(self):
        self.file.close()

    def test_one_step(self):
        self.cut.step()
        self.assertEqual(self.cut.getPosition(),'#')
    
    def test_two_steps(self):
        self.cut.step()
        self.cut.step()
        self.assertEqual(self.cut.getPosition(),'.')

    def test_last_column_behavior(self):
        for i in range(11):
            self.cut.step()
        self.assertEqual(self.cut.getPosition(),'.')

    def test_12_steps(self):
        for i in range(12):
            self.cut.step()
        self.assertEqual(self.cut.getPosition(),'#')
    
    def test_13_steps(self):
        for i in range(13):
            self.cut.step()
        self.assertEqual(self.cut.getPosition(),'.')
    
    def test_14_steps(self):
        for i in range(14):
            self.cut.step()
        self.assertEqual(self.cut.getPosition(),'.')
    
    def test_end_of_map(self):
        with self.assertRaises(IndexError):
            for i in range(20):
                self.cut.step()
            self.cut.getPosition()
    
    def test_solve_slope_3_1(self):
        self.assertEqual(self.cut.solve(3,1),8)
    
    def test_solve_slope_5_2(self):
        self.assertEqual(self.cut.solve(5,2),2)


if __name__ == '__main__':
    unittest.main()
