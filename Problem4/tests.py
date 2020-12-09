import unittest
from solution import solver
from solution import parsePuzzleInputFrom
from solution import passport

class getResults(unittest.TestCase):
    pass

class parseFile(unittest.TestCase):
    def setUp(self) -> None:
        import os
        relPath = 'PuzzleInputTest.txt'
        absFilePath = os.path.dirname(__file__)
        absFilePath = os.path.join(absFilePath, relPath)
        self.file = open(absFilePath, 'r')
        self.result = parsePuzzleInputFrom(self.file)
        self.file.close()

    def tearDown(self) -> None:
        pass

    def test_return_type(self):
        self.assertIsInstance(self.result[0], passport)

    def test_return_count(self):
        self.assertEqual(len(self.result),4)

    def test_passport_parsing(self):
        passport = self.result[0]
        self.assertEqual(passport.eyecolor, 'gry')
        self.assertEqual(passport.passid, '860033327')
        self.assertEqual(passport.expirationYear, '2020')
        self.assertEqual(passport.hairColor, '#fffffd')
        self.assertEqual(passport.birthYear, '1937')
        self.assertEqual(passport.issueYear, '2017')
        self.assertEqual(passport.countryID, '147')
        self.assertEqual(passport.height, '183cm')
        passport = self.result[1]
        self.assertEqual(passport.invalid, True)
        passport = self.result[2]
        self.assertEqual(passport.eyecolor, 'brn')
        self.assertEqual(passport.passid, '760753108')
        self.assertEqual(passport.expirationYear, '2024')
        self.assertEqual(passport.hairColor, '#ae17e1')
        self.assertEqual(passport.birthYear, '1931')
        self.assertEqual(passport.issueYear, '2013')
        self.assertEqual(passport.height, '179cm')
        self.assertEqual(passport.invalid, False)
        passport = self.result[3]
        self.assertEqual(passport.invalid, True)

class validations(unittest.TestCase):
    def setUp(self):
        self.attributes = {'ecl':'gry','pid':'860033327','eyr':'2020','hcl':'#fffffd','byr':'1920','iyr':'2010','cid':'147','hgt':'183cm'}
        self.assertValid()
    def assertValid(self):
        cut = passport(self.attributes)
        self.assertFalse(cut.invalid)
    def assertInvalid(self):
        cut = passport(self.attributes)
        self.assertTrue(cut.invalid)
    
    def test_BirthYearTooLow(self):
        self.attributes['byr'] = '1919'
        self.assertInvalid()
    def test_BirthYearTooHigh(self):
        self.attributes['byr'] = '2003'
        self.assertInvalid()
    def test_BirthYearFitsUpper(self):
        self.attributes['byr'] = '2002'
        self.assertValid()
    
    def test_IssueYearTooLow(self):
        self.attributes['iyr'] = '2009'
        self.assertInvalid()
    def test_IssueYearTooHigh(self):
        self.attributes['iyr'] = '2021'
        self.assertInvalid()
    def test_IssueYearFitsUpper(self):
        self.attributes['iyr'] = '2020'
        self.assertValid()

    def test_ExpirationYearTooLow(self):
        self.attributes['eyr'] = '2019'
        self.assertInvalid()
    def test_ExpirationYearTooHigh(self):
        self.attributes['eyr'] = '2031'
        self.assertInvalid()
    def test_ExpirationYearFitsUpper(self):
        self.attributes['eyr'] = '2030'
        self.assertValid()

    def test_HeightNeitherInchNorCm(self):
        self.attributes['hgt'] = '123dm'
        self.assertInvalid()
        self.attributes['hgt'] = '23dm'
        self.assertInvalid()

    def test_TooBig(self):
        self.attributes['hgt'] = '194cm'
        self.assertInvalid()
        self.attributes['hgt'] = '77in'
        self.assertInvalid()

    def test_TooSmall(self):
        self.attributes['hgt'] = '149cm'
        self.assertInvalid()
        self.attributes['hgt'] = '58in'
        self.assertInvalid()

    def test_JustNotTooBigNorTooSmall(self):
        self.attributes['hgt'] = '150cm'
        self.assertValid()
        self.attributes['hgt'] = '59in'
        self.assertValid()
        self.attributes['hgt'] = '193cm'
        self.assertValid()
        self.attributes['hgt'] = '76in'
        self.assertValid()

    def test_invalidHairColor(self):
        self.attributes['hcl'] = '123456d'
        self.assertInvalid()

if __name__ == '__main__':
    unittest.main()
