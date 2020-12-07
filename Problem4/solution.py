class passport:
    def __init__(self, attributes ):
        self.invalid = False
        try:
            self.countryID = attributes['cid']
        except KeyError:
            self.invalid = False
        try:
            self.passid = attributes['pid']
            self.issueYear = attributes['iyr']
            self.expirationYear = attributes['eyr']
            self.birthYear = attributes['byr']
            self.height = attributes['hgt']
            self.eyecolor = attributes['ecl']
            self.hairColor = attributes['hcl']
        except KeyError: 
            self.invalid = True
        if not self.invalid:
            self.validate()
    
    def validate(self):
        self.validateYears()
        self.validateHeight()
        self.validateHairColor()
        self.validateEyeColor()
        self.validatePassportID()

    def validateHeight(self):
        try:
            if len(self.height) == 4:
                if self.height[2:] != 'in':
                    self.invalid = True
                elif int(self.height[0:2]) < 59 or int(self.height[0:2]) > 76:
                    self.invalid = True
            elif len(self.height) == 5:
                if self.height[3:] != 'cm':
                    self.invalid = True
                elif int(self.height[0:3]) < 150 or int(self.height[0:3]) > 193:
                    self.invalid = True
            else:
                self.invalid = True
        except ValueError:
            self.invalid = True

    def validateHairColor(self):
        import string
        if self.hairColor[0] != '#' or len(self.hairColor) != 7:
            self.invalid = True 
        elif not ( set(self.hairColor[1:]) <= set(string.digits+'abcdef') ):
            self.invalid = True

    def validateEyeColor(self):
        if not self.eyecolor in set(['amb','blu','brn','gry','grn','hzl','oth']):
            self.invalid = True

    def validatePassportID(self):
        if len(self.passid) != 9:
            self.invalid = True
        try:
            int(self.passid)
        except ValueError:
            self.invalid = True

    def validateYears(self):
        if int(self.birthYear)>2002 or int(self.birthYear)<1920:
            self.invalid = True
        if int(self.issueYear)>2020 or int(self.issueYear)<2010:
            self.invalid = True
        if int(self.expirationYear)>2030 or int(self.expirationYear)<2020:
            self.invalid = True
class solver:
    def __init__(self,puzzleInput):
        self.passports = puzzleInput
    def solve(self):    
        result = 0
        for passport in self.passports:
            if not passport.invalid:
                result += 1
        return result

def parseFile():
    import os
    relPath = 'PuzzleInput.txt'
    absFilePath = os.path.abspath(__file__)
    absFilePath = os.path.dirname(__file__)
    absFilePath = os.path.join(absFilePath, relPath)

    with open(absFilePath,'r') as file:
        result = parsePuzzleInputFrom(file)
    return result

def parsePuzzleInputFrom(file):
    result = []
    passports = file.readlines()
    attributes = {}
    for i in range(len(passports)):
        line = passports[i].strip('\n')
        if len(line) == 0:
            result.append(passport(attributes))
            del attributes
            attributes = {}
        else:    
            while len(line)>0:
                space = line.find(' ')
                delimiter = line.find(':')
                if space == -1:
                    kwAndValue = line
                    line = ''
                else:    
                    kwAndValue = line[0:space]
                    line = line[space+1:]
                kw = kwAndValue[0:delimiter]
                value = kwAndValue[delimiter+1:] 
                attributes[kw] = value
    result.append(passport(attributes))
    return result


if __name__ == '__main__':
    puzzleInput = parseFile( )
    print(solver(puzzleInput).solve())

