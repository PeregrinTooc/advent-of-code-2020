import unittest
from solution import solver
from solution import parsePuzzleInputFrom
from solution import NoKeyException


def parseFile(filename='PuzzleInputTest.txt'):
    import os
    relPath = filename
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)
    with open(absFilePath, 'r') as file:
        result = parsePuzzleInputFrom(file)
    return result


class getResults(unittest.TestCase):
    def setUp(self) -> None:
        self.parsedInput = parseFile()
        self.cut = solver(self.parsedInput, 5)

    def assertActualEquals(self, exp):
        self.assertEqual(self.act, exp)

    def test_findKeyForLineSix(self):
        self.act = self.cut.findKeyForLine(5)
        self.assertActualEquals([15, 25])

    def test_findKeyForLineSeven(self):
        self.act = self.cut.findKeyForLine(6)
        self.assertActualEquals([15, 47])

    def test_ExceptionOnNoKey(self):
        with self.assertRaises(NoKeyException):
            self.act = self.cut.findKeyForLine(14)

    def test_Solve(self):
        self.assertEqual(self.cut.solve(), 127)

    def test_findVulnerableLine(self):
        line = 0
        try:
            for i in range(6, 20):
                line = i
                self.cut.findKeyForLine(line)
        except NoKeyException:
            self.assertEqual(line, 14)

    def test_findContinuousSequenceAddingUpTo(self):
        self.cut.findContinuousSequenceAddingUpTo(127)
        pass


class parsePuzzleInput(unittest.TestCase):
    def setUp(self) -> None:
        self.parsedInput = parseFile()

    def test_firstLine(self):
        self.assertEqual(self.parsedInput[0], 20)

    def test_secondLine(self):
        self.assertEqual(self.parsedInput[2], 15)

    def test_fileLength(self):
        self.assertEqual(len(self.parsedInput), 20)


if __name__ == '__main__':
    unittest.main()
