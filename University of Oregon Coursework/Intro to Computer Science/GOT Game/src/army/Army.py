__author__ = 'Sam'

from src.common.observer import *
from src.common.battleDriver import *
from src.common.GameWorld import *
from src.common.Visitor import *
from Graphics.drawable import *
from armyState import *


class GameUnit(Observable,IVisitor,GameDrawable):
    def __init__(self):
        super(GameUnit, self).__init__()
        self.positionX = 0
        self.positionY = 0
        self.name = ''
        self.picture =  None
        self.size = 0
    def move(self,x,y):
        pass
    def setState(self,newState):
        pass
    def wait(self):
        pass
    def visit(self,cityList):
        pass
    def reset(self,gold):
        pass


class Army(GameUnit, Observer):
    def __init__(self, x,y ,control,size):
        super(Army, self).__init__()
        self.positionX = x
        self.positionY = y
        self.control = control
        self.powerState = NormalState(self)             ####STATE PATTERN####
        self.picture = self.powerState.getPic(self)
        self.size = size
        self.currentLoc = None
        self.action = None

    def move(self,x,y):
        super(Army, self).move(x,y)
        self.positionX = x
        self.positionY = y
        self.action = 'move'

    def setState(self, newState):                   ####STATE PATTERN####
        super(Army,self).setState(newState)
        self.powerState = newState(self)
        self.picture = self.powerState.getPic(self)
        self.notifyObservers(self)                  ####OBSERVER PATTERN####

    def wait(self):
        super(Army,self).wait()
        self.action = 'wait'
        self.notifyObservers(self)

    def visit(self,city):
        city.accept(self)

    def update(self,army):
        super(Army,self).update(army)
        pass

    def reset(self,gold):
        super(Army,self).reset(gold)
        return gold




