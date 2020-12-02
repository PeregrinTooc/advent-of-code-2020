import unittest
from solution  import solver
from solution import parsePuzzleInputFrom

class getResults(unittest.TestCase):
    pass

class parseFile(unittest.TestCase):
    def setUp(self) -> None:
        import os
        relPath = 'PuzzleInputTest.txt'
        absFilePath = os.path.dirname(__file__)
        absFilePath = os.path.join(absFilePath, relPath)
        self.file = open(absFilePath, 'r')

    def tearDown(self) -> None:
        self.file.close()

    
if __name__ == '__main__':
    unittest.main()
