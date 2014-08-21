__author__ = 'Sam'
from src.army.Army import *
from src.common.cities import *
import time

class GameSet(object):
    def __init__(self):
        super(GameSet,self).__init__()
        self.playerArmy = None
        self.compArmy = None
        self._playerCities = list()
        self._compCities = list()
        self._playerGold = 0
        self._compGold = 0
        self._drawable = list()
        self._turn = 0
        self._gameCanvas = None
        self._cities = []
        self.playerArmyPos = 0
        self.compArmyPos = 6

    def newGame(self):
        riverrun = Riverrun()
        kingsLanding = KingsLanding()
        goldenTooth = GoldenTooth()
        silverhill = Silverhill()
        stonySept = StonySept()
        harrenhal = Harrenhal()
        maidenpool = Maidenpool()
        self._playerCities.append(riverrun)
        self._compCities.append(kingsLanding)
        self._playerGold = 0
        self._compGold = 0
        self._cities.append(riverrun)
        self._cities.append(goldenTooth)
        self._cities.append(silverhill)
        self._cities.append(stonySept)
        self._cities.append(harrenhal)
        self._cities.append(maidenpool)
        self._cities.append(kingsLanding)

    def getCityList(self):
        return self._cities

    def setGameCanvas(self, gameCanvas):
        assert self._gameCanvas is None
        self._gameCanvas = gameCanvas
        gameCanvas.world = self

    def getGameCanvas(self):
        return self._gameCanvas

    def addArmy(self,army):
        if army.control == 'user':
            self.playerArmy = army
            self.playerArmyPos = 0
            self._cities[0].control = army
        else:
            self.compArmy = army
            self.compArmyPos = 6
            self._cities[6].control = army
        self._drawable.append(army)
        army.addObserver(self.gameCanvas)           ####OBSERVER PATTERN####

    def resetArmy(self,army):
        if army.control == 'user':
            self.playerArmyPos = 0
            self.playerArmy.size = army.reset(self._playerGold)
            army.notifyObservers(army)              ####OBSERVER PATTERN####
            self._playerGold = 0
            self.playerArmy.positionX = 540
            self.playerArmy.positionY = 10
        else:
            self.compArmyPos = 6
            self.compArmy.size = army.reset(self._compGold)   ####DECORATOR PATTERN####
            self._compGold = 0
            self.compArmy.positionX = 1050
            self.compArmy.positionY = 490
        army.setState(NormalState)
        self.gameCanvas.redraw(self._drawable)

    def getGold(self):
        return str(self._playerGold)

    def endTurn(self):
        if self.compArmy.size >= 100 and self._compGold >= 500:
            self.compArmy.setState(HeroState)
            self._compGold -= 500
            self.armyMove()
        elif self.compArmy.size >= 100:
            self.armyMove()
        else:
            self.compArmy.action = 'wait'
        self.finalStages()

    def checkSiege(self):
        return self.playerArmyPos == self.compArmyPos

    def armyMove(self):
        moveIndex = self.compArmyPos - 1
        self.compArmyPos = moveIndex
        self.compArmy.positionX = self._cities[moveIndex].positionX
        self.compArmy.positionY = self._cities[moveIndex].positionY
        self.compArmy.action = "move"
        self._gameCanvas.visit(self._cities[moveIndex],self.compArmy)
        self.gameCanvas.redraw(self._drawable)

    def finalStages(self):
        if self.compArmy.size == 0:
            self.resetArmy(self.compArmy)
        if self.playerArmy.size == 0:
            self.resetArmy(self.playerArmy)
        if self._cities[0].control == self.compArmy:
            self.gameCanvas.compVictory()
            #time.sleep(5)
            #self.endGame()
        elif self._cities[6].control == self.playerArmy:
            self.gameCanvas.playerVictory()
            #time.sleep(5)
            #self.endGame()
            return
        for city in self._cities:
            if city.control:
                if city.control == self.playerArmy:
                    self._playerGold += city.value
                elif city.control == self.compArmy:
                    self._compGold += city.value
        if self.playerArmy.action == 'wait':
            self.playerArmy.size += self.playerArmy.powerState.waitBonus()
        if self.compArmy.action == 'wait':
            self.compArmy.size += self.compArmy.powerState.waitBonus()
        self.gameCanvas.redraw(self._drawable)

    def endGame(self):
        exit()

    gameCanvas = property(getGameCanvas,setGameCanvas, None, "Where the game is drawn")
