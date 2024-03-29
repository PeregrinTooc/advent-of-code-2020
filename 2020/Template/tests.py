import unittest
import solution


def parseFile(filename='PuzzleInputTest.txt'):
    import os
    relPath = filename
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)
    with open(absFilePath, 'r') as file:
        result = solution.parsePuzzleInputFrom(file)
    return result


class getResults(unittest.TestCase):
    pass


class parsePuzzleInput(unittest.TestCase):
    def setUp(self) -> None:
        self.parsedInput = parseFile()


if __name__ == '__main__':
    unittest.main()
