import unittest
from solution  import solver
from solution import parsePuzzleInputFromPart1 as parsePuzzleInputFrom

class parseFile(unittest.TestCase):
    def setUp(self) -> None:
        import os
        relPath = 'PuzzleInputTest.txt'
        absFilePath = os.path.dirname(__file__)
        absFilePath = os.path.join(absFilePath, relPath)
        self.file = open(absFilePath, 'r')

    def tearDown(self) -> None:
        self.file.close()

    def test_resultContainsSets(self):
        self.assertIsInstance(parsePuzzleInputFrom(self.file)[0],set)
    
    def test_Set1(self):
        exp = set(['a','b','c'])
        self.assertSetEqual(exp,parsePuzzleInputFrom(self.file)[0])

    def test_Set2(self):
        exp = set(['a','b','c'])
        self.assertSetEqual(exp,parsePuzzleInputFrom(self.file)[1])

    def test_Set3(self):
        exp = set(['a','b','c'])
        self.assertSetEqual(exp,parsePuzzleInputFrom(self.file)[2])

    def test_Set4(self):
        exp = set(['a'])
        self.assertSetEqual(exp,parsePuzzleInputFrom(self.file)[3])

    def test_Set5(self):
        exp = set(['b'])
        self.assertSetEqual(exp,parsePuzzleInputFrom(self.file)[4])

    def test_result(self):
        act = solver(parsePuzzleInputFrom(self.file)).solve()
        exp = 11
        self.assertEqual(act,exp)

if __name__ == '__main__':
    unittest.main()
