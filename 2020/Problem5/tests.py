import unittest
from solution import solver
from solution import parsePuzzleInputFrom
from solution import seatParser


class getRow(unittest.TestCase):
    def test_FBFBBFFRLR(self):
        actualRow = seatParser('FBFBBFFRLR').getRow()
        expectedRow = 44
        self.assertEqual(actualRow, expectedRow)

    def test_FFFFFFFLLL(self):
        actualRow = seatParser('FFFFFFFLLL').getRow()
        expectedRow = 0
        self.assertEqual(actualRow, expectedRow)

    def test_BFFFFFFLLL(self):
        actualRow = seatParser('BFFFFFFLLL').getRow()
        expectedRow = 64
        self.assertEqual(actualRow, expectedRow)

    def test_BBFFFFFLLL(self):
        actualRow = seatParser('BBFFFFFLLL').getRow()
        expectedRow = 96
        self.assertEqual(actualRow, expectedRow)


class getSeatNumber(unittest.TestCase):
    def test_FFFFFFFLLL(self):
        actualSeat = seatParser('FFFFFFFLLL').getSeat()
        expectedSeat = 0
        self.assertEqual(actualSeat, expectedSeat)

    def test_FFFFFFFRLL(self):
        actualSeat = seatParser('FFFFFFFRLL').getSeat()
        expectedSeat = 4
        self.assertEqual(actualSeat, expectedSeat)

    def test_FFFFFFFLRL(self):
        actualSeat = seatParser('FFFFFFFLRL').getSeat()
        expectedSeat = 2
        self.assertEqual(actualSeat, expectedSeat)


class getSeatId(unittest.TestCase):
    def test_MultipleIds(self):
        self.assertEqual(seatParser('FBFBBFFRLR').getSeatId(), 357)
        self.assertEqual(seatParser('BFFFBBFRRR').getSeatId(), 567)
        self.assertEqual(seatParser('FFFBBBFRRR').getSeatId(), 119)
        self.assertEqual(seatParser('BBFFBBFRLL').getSeatId(), 820)
        self.assertEqual(seatParser('FFFFFFFLLL').getSeatId(), 0)
        self.assertEqual(seatParser('BBBBBBBRRR').getSeatId(), 1023)


class parseFile(unittest.TestCase):
    def setUp(self) -> None:
        import os
        relPath = 'PuzzleInputTest.txt'
        absFilePath = os.path.dirname(__file__)
        absFilePath = os.path.join(absFilePath, relPath)
        file = open(absFilePath, 'r')
        self.parsed = parsePuzzleInputFrom(file)
        file.close()

    def test_firstLine(self):
        self.assertEqual('FBFBBFFRLR', self.parsed[0])

    def test_secondLine(self):
        self.assertEqual('BFFFBBFRRR', self.parsed[1])

    def test_solver(self):
        self.assertEqual(solver(self.parsed).solve(), 820)


if __name__ == '__main__':
    unittest.main()
