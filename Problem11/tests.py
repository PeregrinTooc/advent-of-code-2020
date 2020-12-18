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


class identifyStable(unittest.TestCase):
    def setUp(self):
        self.gridSizeFourOnlySeats = [
            ['L' for _ in range(4)] for _ in range(4)]

    def test_oneTickSize4NoFloor(self):
        cut = solution.SeatingGrid(self.gridSizeFourOnlySeats)
        cut.tick()
        self.assertFalse(cut.isStable())
        cut.tick()
        self.assertFalse(cut.isStable())
        cut.tick()
        self.assertTrue(cut.isStable())


class countOccupiedWhenStable(unittest.TestCase):
    def setUp(self):
        self.gridSizeFourOnlySeats = [
            ['L' for _ in range(4)] for _ in range(4)]
        self.puzzleGrid = parseFile()

    def test_SizeFourOnlySeats(self):
        self.assertEqual(solution.solver(
            grid=self.gridSizeFourOnlySeats).solve(), 4)

    def test_SizeFourOnlySeats(self):
        self.assertEqual(solution.solver(
            seats=self.puzzleGrid).solve(), 37)


class Tick(unittest.TestCase):
    def setUp(self):
        self.gridSizeFourOnlySeats = [
            ['L' for _ in range(4)] for _ in range(4)]
        self.gridSizeFourOneColumnFloor = [
            ['L' for _ in range(4)] for _ in range(4)]
        for i in range(4):
            self.gridSizeFourOneColumnFloor[i][0] = "."

    def test_oneTickSize4NoFloor(self):
        cut = solution.SeatingGrid(self.gridSizeFourOnlySeats)
        cut.tick()
        for i in range(4):
            for j in range(4):
                self.assertTrue(cut.getSeatAt(i, j).isOccupied())

    def test_twoTicksSize4NoFloor(self):
        cut = solution.SeatingGrid(self.gridSizeFourOnlySeats)
        for _ in range(2):
            cut.tick()
        self.assertTrue(cut.getSeatAt(0, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 3).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(0, 3).isOccupied())
        for i in range(4):
            for j in range(1, 3):
                self.assertFalse(cut.getSeatAt(i, j).isOccupied())

    def test_ThreeTicksSize4NoFloor(self):
        cut = solution.SeatingGrid(self.gridSizeFourOnlySeats)
        for _ in range(3):
            cut.tick()
        self.assertTrue(cut.getSeatAt(0, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 3).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(0, 3).isOccupied())
        for i in range(4):
            for j in range(1, 3):
                self.assertFalse(cut.getSeatAt(i, j).isOccupied())

    def test_oneTickSize41ColumnFloor(self):
        cut = solution.SeatingGrid(self.gridSizeFourOnlySeats)
        cut.tick()
        for i in range(4):
            for j in range(1, 4):
                self.assertTrue(cut.getSeatAt(i, j).isOccupied())
            self.assertTrue(cut.getSeatAt(i, 0).isOccupied())

    def test_twoTicksSize41ColumnFloor(self):
        cut = solution.SeatingGrid(self.gridSizeFourOneColumnFloor)
        cut.tick()
        for i in range(4):
            for j in range(1, 4):
                self.assertTrue(cut.getSeatAt(i, j).isOccupied())
            self.assertFalse(cut.getSeatAt(i, 0).isOccupied())

    def test_twoTicksSize41ColumnFloor(self):
        cut = solution.SeatingGrid(self.gridSizeFourOneColumnFloor)
        for _ in range(2):
            cut.tick()
        self.assertTrue(cut.getSeatAt(1, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 3).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(1, 3).isOccupied())

    def test_threeTicksSize41ColumnFloorStable(self):
        cut = solution.SeatingGrid(self.gridSizeFourOneColumnFloor)
        for _ in range(3):
            cut.tick()
        self.assertTrue(cut.getSeatAt(1, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 3).isOccupied())
        self.assertTrue(cut.getSeatAt(3, 0).isOccupied())
        self.assertTrue(cut.getSeatAt(1, 3).isOccupied())


class parsePuzzleInput(unittest.TestCase):
    def setUp(self) -> None:
        self.parsedInput = parseFile()

    def test_firstSeat(self):
        self.assertEqual(self.parsedInput.getSeatAt(0, 0).isOccupied(), False)

    def test_firstSeatNotFloor(self):
        self.assertEqual(self.parsedInput.getSeatAt(0, 0).isFloor(), False)

    def test_secondSeat(self):
        self.assertEqual(self.parsedInput.getSeatAt(1, 0).isOccupied(), False)
        self.assertEqual(self.parsedInput.getSeatAt(1, 0).isFloor(), True)

    def test_lastSeat(self):
        self.assertEqual(self.parsedInput.getSeatAt(9, 9).isOccupied(), False)
        self.assertEqual(self.parsedInput.getSeatAt(9, 9).isFloor(), False)


if __name__ == '__main__':
    unittest.main()
