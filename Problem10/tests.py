import unittest
from solution import solver
from solution import parsePuzzleInputFrom


def parseFile(filename='PuzzleInputTest.txt'):
    import os
    relPath = filename
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)
    with open(absFilePath, 'r') as file:
        result = parsePuzzleInputFrom(file)
    return result


class getResults(unittest.TestCase):
    def test_simpleDistribution(self) -> None:
        self.cut = solver([1, 2])
        self.exp = [2, 0, 1]
        self.assertDistribution()
        self.assertEqual(self.cut.solve(), 2)

    def assertDistribution(self):
        act = self.cut.getDistribution()
        self.assertEqual(act, self.exp)

    def test_anotherSimpleDistribution(self) -> None:
        self.cut = solver([1, 2, 3])
        self.exp = [3, 0, 1]
        self.assertDistribution()
        self.assertEqual(self.cut.solve(), 3)

    def test_aBitComplexDistribution(self) -> None:
        self.cut = solver([1, 2, 3, 5])
        self.exp = [3, 1, 1]
        self.assertDistribution()

    def test_aBitComplexDistribution(self) -> None:
        self.cut = solver([1, 4, 5, 7])
        self.exp = [2, 1, 2]
        self.assertDistribution()


class parsePuzzleInput(unittest.TestCase):
    def setUp(self) -> None:
        self.parsedInput = parseFile()

    def test_LowestElement(self):
        self.assertEqual(self.parsedInput[0], 1)

    def test_length(self):
        self.assertEqual(len(self.parsedInput), 31)

    def test_highestElement(self):
        self.assertEqual(self.parsedInput[30], 49)


if __name__ == '__main__':
    unittest.main()
