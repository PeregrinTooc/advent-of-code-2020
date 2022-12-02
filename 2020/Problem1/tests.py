import unittest
from solution import solver

class getResults(unittest.TestCase):
    def test_onlyTwoNumbers(self):
        testdata  = [2019,1]
        cut = solver( testdata )
        self.assertEqual(cut.getResultForTwo(), 2019)
    
    def test_ThreeNumbers(self):
        testdata  = [1,20, 2000]
        cut = solver( testdata )
        self.assertEqual(cut.getResultForTwo(), 2000*20)

if __name__ == '__main__':
    unittest.main()
