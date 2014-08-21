__author__ = 'Sam'

from src.common.observer import *
from src.common.Visitor import *
from src.common.battleDriver import *

                                            ####STRATEGY PATTERN#####

class ImportanceBehavior(object):
    def importance(self):
        pass

class HI(ImportanceBehavior):
    def importance(self):
        return 20

class MID(ImportanceBehavior):
    def importance(self):
        return 10

class LO(ImportanceBehavior):
    def importance(self):
        return 0

class City(Observable,IVisitable):
    def __init__(self):
        super(City,self).__init__()
        self.name = ''
        self.defense = 0
        self.positionX = 0
        self.positionY = 0
        self.importanceBehavior = ImportanceBehavior()
        self.control = None
        self.value = 0


    def accept(self,visitor,army):
        visitor.visit(self,army)


    def update(self,army):
        if army.positionX == self.positionX and army.positionY == self.positionY:
            if self.control == None and army.action == 'wait':
                self.control = army



class Riverrun(City, Observable):
    def __init__(self):
        super(Riverrun,self).__init__()
        self.name = "Riverrun"
        self.defense = 85
        self.positionX = 540
        self.positionY = 10
        self.importanceBehavior = HI()
        self.value = 100


class GoldenTooth(City, Observable):
    def __init__(self):
        super(GoldenTooth,self).__init__()
        self.name = "Golden Tooth"
        self.defense = 10
        self.positionX = 320
        self.positionY = 200
        self.importanceBehavior = LO()
        self.value = 25

class Silverhill(City, Observable):
    def __init__(self):
        super(Silverhill,self).__init__()
        self.name = "Silverhill"
        self.defense = 25
        self.positionX = 325
        self.positionY = 510
        self.importanceBehavior = MID()
        self.value = 25

class StonySept(City, Observable):
    def __init__(self):
        super(StonySept,self).__init__()
        self.name = "Stony Sept"
        self.defense = 43
        self.positionX = 610
        self.positionY = 290
        self.importanceBehavior = MID()
        self.value = 40

class Harrenhal(City, Observable):
    def __init__(self):
        super(Harrenhal,self).__init__()
        self.name = "Harrenhal"
        self.defense = 70
        self.positionX = 850
        self.positionY = 90
        self.importanceBehavior = HI()
        self.value = 30

class Maidenpool(City, Observable):
    def __init__(self):
        super(Maidenpool,self).__init__()
        self.name = "Maidenpool"
        self.defense = 25
        self.positionX = 1130
        self.positionY = 120
        self.importanceBehavior = LO()
        self.value = 10

class KingsLanding(City, Observable):
    def __init__(self):
        super(KingsLanding,self).__init__()
        self.name = "Kings Landing"
        self.defense = 100
        self.positionX = 1050
        self.positionY = 490
        self.importanceBehavior = HI()
        self.value = 100