import unittest
from solution  import solver1
from solution  import solver2
from solution import parsePuzzleInputFrom

class isValidPassword(unittest.TestCase):
    def setUp(self):
        self.cut = solver2()

    def test_empty_input_invalid(self):
        with self.assertRaises( IndexError ):
            self.cut.isValidPassword( [] ) 
    
    def test_invalid_due_to_not_there(self):
        self.assertFalse( self.cut.isValidPassword( [1,2,'a','bc'] ) )
    
    def test_valid_pos1(self):
        self.assertTrue( self.cut.isValidPassword( [1,2,'a','ab'] ) )

    def test_valid_pos2(self):
        self.assertTrue( self.cut.isValidPassword( [1,2,'a','ba'] ) )

    def test_invalid_both_positions(self):
        self.assertFalse( self.cut.isValidPassword( [1,2,'a','aa'] ) )


class getResults(unittest.TestCase):
    def setUp(self):
        self.cut = solver1()

    def test_empty_input_invalid(self):
        with self.assertRaises( IndexError ):
            self.cut.isValidPassword( [] ) 

    def test_invalid_due_to_too_few(self):
        self.assertFalse( self.cut.isValidPassword( [1,1,'a',''] ) )

    def test_invalid_due_to_too_many(self):
        self.assertFalse( self.cut.isValidPassword( [1,1,'a','aa'] ) )

    def test_valid_minimal(self):
        self.assertTrue( self.cut.isValidPassword( [1,1,'a','a'] ) )
    
    def test_solve_to_0(self):
        self.cut.setPasswords( [[1,1,'a',''],[1,1,'a','aa']] )
        self.assertEqual(self.cut.solve(),0)

    def test_solve_to_1(self):
        self.cut.setPasswords( [[1,1,'a',''],[1,1,'a','a']] )
        self.assertEqual(self.cut.solve(),1)


class parseFile(unittest.TestCase):
    def setUp(self):
        import os
        relPath = 'PuzzleInputTest.txt'
        absFilePath = os.path.dirname(__file__)
        absFilePath = os.path.join(absFilePath, relPath)
        self.file = open(absFilePath, 'r')
        self.result = parsePuzzleInputFrom(self.file)
    def tearDown(self):
        self.file.close()
    
    def test_parse(self):
        self.assertEqual( self.result, [[1,3,'a','abcde'],[11,12,'x','xxxxxxxxxxxz']])
    
    def test_length(self):
        self.assertEqual( len(self.result),2 )

    def test_firstElement(self):
        self.assertEqual( self.result[0][0],1)
        self.assertEqual( self.result[1][0],11)
    
    def test_secondElement(self):
        self.assertEqual( self.result[0][1],3)
        self.assertEqual( self.result[1][1],12)
    
    def test_thirdElement(self):
        self.assertEqual( self.result[0][2],'a')
        self.assertEqual( self.result[1][2],'x')
    
if __name__ == '__main__':
    unittest.main()