__author__ = 'Sam Vitello'
"""
Input: JSON 2D Array
e.g.
[[5,6,7],
[2,5,6],
[2,3,5]]

Output: Greatest path from Top Left to Bottom Right only moving down and right
e.g.
Path: RRDD
Value: 31

Built for Python Interpreter v.3.2.3
"""
import json
import sys

############################################################################################### Path Node ##############

class PathNode():
    def __init__(self):
        self._value = None
        self._pathValue = ""

    def setValue(self, val):
        self._value = val

    def getValue(self):
        return self._value

    def setPath(self, val):
        self._pathValue = val

    def getPath(self):
        return self._pathValue

############################################################################################### Largest Path ###########

def largestPath(inputArray):
    """
    :param inputArray (2D Array):
    :return: PathNode containing greatest value and path from [0][0] to [size][size]
    """
    rows = len(inputArray)
    cols = len(inputArray[0])

    greatestPaths = [[PathNode() for i in range(cols)] for j in range(rows)]

    ## Set Path Start Value ##
    greatestPaths[0][0].setValue(inputArray[0][0])

    ## Set Values along Top and Left Sides of Matrix ##
    for i in range(1, rows):
        initRowVal = greatestPaths[i][0]
        initRowVal.setValue(greatestPaths[i-1][0].getValue() + inputArray[i][0])
        initRowVal.setPath('D' * i)

    for j in range(1, cols):
        initColVal = greatestPaths[0][j]
        initColVal.setValue(greatestPaths[0][j-1].getValue() + inputArray[0][j])
        initColVal.setPath('R' * j)

    ## Use Previously Values to Find Max Path ##
    for r in range(1, rows):
        for c in range(1, cols):
            inheritUp = greatestPaths[r-1][c]
            inheritLeft = greatestPaths[r][c-1]
            newNode = greatestPaths[r][c]
            if inheritUp.getValue() > inheritLeft.getValue():
                newNode.setValue(inheritUp.getValue() + inputArray[r][c])
                newNode.setPath(inheritUp.getPath() + 'D')
            else:
                newNode.setValue(inheritLeft.getValue() + inputArray[r][c])
                newNode.setPath(inheritLeft.getPath() + 'R')

    return greatestPaths[rows-1][cols-1]


############################################################################################### MAIN ###################

if __name__ == "__main__":
    if len(sys.argv) != 2 or not sys.argv[1].endswith('.json'):
        print("Requires .json file as only argument")
        quit()
    json_file = open(sys.argv[1])
    inputArray = json.load(json_file)

    greatestPath = largestPath(inputArray)

    print("Path: " + greatestPath.getPath())
    print("Value: " + str(greatestPath.getValue()))





