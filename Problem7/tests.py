import unittest
from solution  import solver
from solution import parsePuzzleInputFrom
from solution import rule

class getResults(unittest.TestCase):
    pass

class parseFile(unittest.TestCase):
    def setUp(self) -> None:
        import os
        relPath = 'PuzzleInputTest.txt'
        absFilePath = os.path.dirname(__file__)
        absFilePath = os.path.join(absFilePath, relPath)
        with open(absFilePath, 'r') as file:
            self.parsedInput = parsePuzzleInputFrom(file)

    def test_resultConsistsOfRules(self):
        for line in self.parsedInput:
            self.assertIsInstance(line,rule)

    def test_firstRuleParameters(self):
        actRule = self.parsedInput[0]
        self.assertEqual(actRule.getContainer().getColor(),'light red')
        self.assertEqual(actRule.getContained()[0]['count'],1)
        self.assertEqual(actRule.getContained()[0]['bag'].getColor(),'bright white')
        self.assertEqual(actRule.getContained()[1]['count'],2)
        self.assertEqual(actRule.getContained()[1]['bag'].getColor(),'muted yellow')

    def test_secondRuleParameters(self):
        actRule = self.parsedInput[1]
        self.assertEqual(actRule.getContainer().getColor(),'dark orange')
        self.assertEqual(actRule.getContained()[0]['count'],3)
        self.assertEqual(actRule.getContained()[0]['bag'].getColor(),'bright white')
        self.assertEqual(actRule.getContained()[1]['count'],4)
        self.assertEqual(actRule.getContained()[1]['bag'].getColor(),'muted yellow')
    
    def test_thirdRuleParameters(self):
        actRule = self.parsedInput[2]
        self.assertEqual(actRule.getContainer().getColor(),'bright white')
        self.assertEqual(actRule.getContained()[0]['count'],1)
        self.assertEqual(actRule.getContained()[0]['bag'].getColor(),'shiny gold')

    def test_fourthRuleParameters(self):
        actRule = self.parsedInput[3]
        self.assertEqual(actRule.getContainer().getColor(),'muted yellow')
        self.assertEqual(actRule.getContained()[0]['bag'].getColor(),'shiny gold')
        self.assertEqual(actRule.getContained()[1]['count'],9)
        self.assertEqual(actRule.getContained()[1]['bag'].getColor(),'faded blue')
    
    def test_ninthRuleParameters(self):
        actRule = self.parsedInput[8]
        self.assertEqual(actRule.getContainer().getColor(),'dotted black')
        self.assertEqual(len(actRule.getContained()),0)


if __name__ == '__main__':
    unittest.main()
