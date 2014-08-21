__author__ = 'Sam'

from Army import *
from src.common.observer import *

class ArmyDecorator(Army,Observable):
    def __init__(self,decorated):
        #super(ArmyDecorator, self).__init__()
        self.decorated = decorated

    def setX(self,x):
        self.decorated.positionX = x

    def getX(self):
        return self.decorated.positionX

    positionX = property(getX,setX)

    def setY(self,y):
        self.decorated.positionY = y

    def getX(self):
        return self.decorated.positionY

    positionY = property(getX,setY)

    def setControl(self,control):
        self.decorated.control = control

    def getControl(self):
        return self.decorated.control

    control = property(getControl,setControl)

    def setSize(self,size):
        self.decorated.size = size

    def getSize(self):
        return self.decorated.size

    size = property(getSize,setSize)

    def setCurrentLoc(self,loc):
        self.decorated.currentLoc = loc

    def getCurrentLoc(self):
        return self.decorated.currentLoc

    currentLoc = property(getCurrentLoc,setCurrentLoc)

    def setAction(self,action):
        self.decorated.action = action

    def getAction(self):
        return self.decorated.action

    action = property(getAction,setAction)

    def setObservers(self,obs):
        self.decorated._observers = obs

    def getObservers(self):
        return self.decorated._observers

    _observers = property(getObservers,setObservers)

    def setPicture(self,pic):
        self.decorated._picture = pic

    def getPicture(self):
        return self.decorated._picture

    _picture = property(getPicture,setPicture)

    def setPowerState(self,pwr):
        self.decorated.powerState = pwr

    def getPowerState(self):
        return self.decorated.powerState

    powerState = property(getPowerState,setPowerState)

    def move(self,x,y):
        self.decorated.move(x,y)

    def setState(self, newState):
        self.decorated.setState(newState)

    def getState(self):
        return self.decorated.powerState

    def wait(self):
        self.decorated.wait()

    def visit(self,city):
        self.decorated.visit(city)

    def update(self,army):
        self.decorated.update(army)

    def reset(self,gold):
        return gold

####DECORATOR PATTERN####
class EasyDecorator(ArmyDecorator):
    def reset(self,gold):
        return gold//2

class NormalDecorator(ArmyDecorator):
    pass

class HardDecorator(ArmyDecorator):
    def reset(self,gold):
        return gold + int(gold*.3)