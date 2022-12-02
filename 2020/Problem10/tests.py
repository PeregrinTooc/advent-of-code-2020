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


class getWaysFromStartToEnd(unittest.TestCase):
    def setUp(self) -> None:
        self.cut = solution.solver([1])

    def testForInputAndExpectation(self, input=1, expected=1):
        self.assertEqual(self.cut.getWaysFromStartToEnd(input), expected)

    def test_1(self):
        self.testForInputAndExpectation()

    def test_2(self):
        self.testForInputAndExpectation(2, 1)

    def test_3(self):
        self.testForInputAndExpectation(3, 2)

    def test_4(self):
        # 0,1,2,3,
        self.testForInputAndExpectation(4, 4)

    def test_5(self):
        # 0,1,2,3,4 -> 1+3+3
        self.testForInputAndExpectation(5, 7)

    def test_6(self):
        # 0,1,2,3,4,5 -> 1+4+6+2
        self.testForInputAndExpectation(6, 13)

    def test_7(self):
        # 0,1,2,3,4,5,6 -> 1+5+(5|2))+(5|3))-3+(5|4)-4
        self.testForInputAndExpectation(7, 24)


class breakAtThrees(unittest.TestCase):
    def test_simpleDistribution(self) -> None:
        self.cut = solution.solver([1, 4, 5, 7])
        self.exp = [[0, 1], [4, 5, 7]]
        self.assertBreakUp()

    def test_anotherSimpleDistribution(self) -> None:
        self.cut = solution.solver([1, 4, 5, 6, 7])
        self.exp = [[0, 1], [4, 5, 6, 7]]
        self.assertBreakUp()

    def test_aMoreComplexDistribution(self) -> None:
        self.cut = solution.solver([1, 4, 5, 8, 9, 10])
        self.exp = [[0, 1], [4, 5], [8, 9, 10]]
        self.assertBreakUp()

    def test_aComplexDistribution(self) -> None:
        self.cut = solution.solver([1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19])
        self.exp = [[0, 1], [4, 5, 6, 7], [10, 11, 12], [15, 16], [19]]
        self.assertBreakUp()
        self.assertEqual(self.cut.getPossibleCombinations(), 8)

    def test_puzzleTest(self):
        cut = solution.solver(parseFile())
        self.assertEqual(cut.getPossibleCombinations(), 19208)

    def assertBreakUp(self):
        act = self.cut.breakAtThrees()
        self.assertEqual(act, self.exp)


class getResults(unittest.TestCase):
    def test_simpleDistribution(self) -> None:
        self.cut = solution.solver([1, 2])
        self.exp = [2, 0, 1]
        self.assertDistribution()
        self.assertEqual(self.cut.solve(), 2)

    def assertDistribution(self):
        act = self.cut.calculateDistribution()
        self.assertEqual(act, self.exp)

    def test_anotherSimpleDistribution(self) -> None:
        self.cut = solution.solver([1, 2, 3])
        self.exp = [3, 0, 1]
        self.assertDistribution()
        self.assertEqual(self.cut.solve(), 3)

    def test_aBitComplexDistribution(self) -> None:
        self.cut = solution.solver([1, 2, 3, 5])
        self.exp = [3, 1, 1]
        self.assertDistribution()

    def test_aBitComplexDistribution(self) -> None:
        self.cut = solution.solver([1, 4, 5, 7])
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
