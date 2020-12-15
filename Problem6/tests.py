import unittest

suite = unittest.TestSuite()
suite.addTests(unittest.defaultTestLoader.loadTestsFromName("testsPart1"))
suite.addTests(unittest.defaultTestLoader.loadTestsFromName("testsPart2"))
unittest.TextTestRunner().run(suite)
