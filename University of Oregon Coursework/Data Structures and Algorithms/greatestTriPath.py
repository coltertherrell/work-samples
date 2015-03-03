__author__ = 'Sam Vitello'
"""
Takes *.txt as an argument and prints the largest sum of path from the top of a triangle of integers to the bottom.
Input must be in format:
1
1 1
1 2 1
1 1 1 1

Output:
5

Built for Python Interpreter v.3.2.3
"""

import sys

###########################################################################################  NODE CLASS  ###############

class TrianglePathNode():
    def __init__(self, val):
        self._value = val
        self._pathValue = None

    def getValue(self):
        return self._value

    def setPath(self, val):
        self._pathValue = val

    def getPath(self):
        return self._pathValue



###########################################################################################  MAIN  #####################


if __name__ == "__main__":

    if len(sys.argv) != 2:
        print("Requires .txt file as only argument")
        quit()
    file = open(sys.argv[1], 'r')

    ### ROOT NODE ###
    startValue = list(map(int, file.readline().split()))
    if len(startValue) != 1:
        print("Invalid .txt file : not triangular")
        quit()
    startNode = TrianglePathNode(startValue[0])
    startNode.setPath(startValue[0])

    ##PREVIOUS LINE NODES##
    lastLine = [startNode]
    nodeBuffer = []

    expectedSize = 2

    ## BUILD PATHS ##
    for line in file:

        lineValues = list(map(int, line.split()))
        if len(lineValues) != expectedSize:
            print("Invalid .txt file : not triangular")
            quit()
        expectedSize += 1

        numCount = 0

        for num in lineValues:
            newNode = TrianglePathNode(num)
            nodeBuffer.append(newNode)

            ## FIRST NODE CASE ##
            if numCount == 0:
                newNode.setPath(lastLine[0].getPath() + num)
                numCount += 1


            ## LAST NODE CASE ##
            elif numCount == (len(lineValues)-1):
                newNode.setPath(lastLine[numCount-1].getPath() + num)
                numCount += 1

            ## GENERAL CASE ##
            else:
                leftPath = lastLine[numCount-1].getPath()
                rightPath = lastLine[numCount].getPath()
                newNode.setPath(num + max([leftPath, rightPath]))
                numCount += 1

        ## RESET BUFFERS FOR NEW LINE ##
        lastLine = nodeBuffer
        nodeBuffer = []


    ## SEARCH LAST ROW FOR GREATEST VALUE ##
    greatestPath = lastLine[0].getPath()
    for i in range(1, len(lastLine)):
        nextPath = lastLine[i].getPath()
        if nextPath > greatestPath:
            greatestPath = nextPath


    ## PRINT RESULT ##
    print(greatestPath)