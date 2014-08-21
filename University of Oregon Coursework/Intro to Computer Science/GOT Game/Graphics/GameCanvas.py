__author__ = 'Sam'
import Tkinter as tk
import threading
from Graphics.map import *

class GameCanvas(threading.Thread):
    def __init__(self, width = 1328, height = 784, gameWorld = None, **kwargs):
        super(GameCanvas,self).__init__(**kwargs)
        self.root = tk.Tk()
        self._canvas = tk.Canvas(self.root,width = width, height=height)
        self._canvas.pack()
        self.world = gameWorld
        self._picFile = "../resources/map.gif"
        self._background = tk.PhotoImage(file = self._picFile)
        self._canvas.create_image(0,0,image = self._background, anchor = tk.NW)
        self.move = tk.Button(self.root,text = "Move",command = self.moveArmy).place(x = 300, y = 750)
        self.wait = tk.Button(self.root,text = "Wait",command = self.wait).place(x=500, y = 750)
        self.upgrade = tk.Button(self.root,text = "Upgrade Army (500 Gold)",command = self.upgrade).place(x=700,y=750)
        self.gold = tk.Label(self.root, text = "Gold = "+self.getGold())
        self.size = tk.Label(self.root, text = "Army Size: "+self.getSize())
        self.enemySize = tk.Label(self.root, text = "Enemy Size: " + self.getCompSize())
        self.enemySize.place(x=1200,y=750)
        self.gold.place(x = 1200,y = 10)
        self.size.place(x = 50, y = 10)

    def redraw(self, listOfDrawables):
        self._canvas.delete(tk.ALL)
        self._canvas.create_image(0, 0, image = self._background, anchor = tk.NW)
        for i in listOfDrawables:
            self._canvas.create_image(i.positionX, i.positionY, image = i.getSprite(), anchor = tk.NW)
        self.root.update()
        self.updateGold()
        self.updateSize()

    def getGold(self):
        if self.world == None:
            return "0"
        else:
            return self.world.getGold()

    def updateGold(self):
        self.gold.configure(text = "Gold = "+self.getGold())

    def getSize(self):
        if self.world == None:
            return "0"
        else:
            return str(self.world.playerArmy.size)

    def getCompSize(self):
        if not self.world:
            return '0'
        else:
            return str(self.world.compArmy.size)

    def updateSize(self):
        self.size.configure(text = "Army Size: "+self.getSize())
        self.enemySize.configure(text = "Enemy Size: "+self.getCompSize())


    def moveArmy(self):
        moveIndex = self.world.playerArmyPos + 1
        self.world.playerArmyPos = moveIndex
        self.world.playerArmy.positionX = self.world._cities[moveIndex].positionX
        self.world.playerArmy.positionY = self.world._cities[moveIndex].positionY
        self.world.playerArmy.action = "move"
        self.visit(self.world._cities[moveIndex],self.world.playerArmy)     ####VISITOR PATTERN####
        self.redraw(self.world._drawable)
        self.world.endTurn()

    def wait(self):
        self.world.playerArmy.action = 'wait'
        self.world.endTurn()

    def upgrade(self):                      ####STATE PATTERN####
        if self.world._playerGold >= 500:
            self.world._playerGold -= 500
            self.world.playerArmy.setState(HeroState)

    def visit(self,city,army):                      ####VISITOR PATTERN#####
        if self.world.playerArmyPos == self.world.compArmyPos:
            siegeBattle(self.world.playerArmy,self.world.compArmy,city)
        elif city.control == army:
            pass
        elif city.control != None:
            simpleBattle(army,city)
        else:
            cityBattle(army,city)
                                                ####OBSERVER PATTERN####
    def update(self,army):
        self.redraw(self.world._drawable)

    def playerVictory(self):
        self._canvas.delete(tk.ALL)
        pic = tk.PhotoImage(file = "../resources/win.gif")
        self._canvas.create_image(0, 0, image = pic, anchor = tk.NW)
        self.root.update()

    def compVictory(self):
        self._canvas.delete(tk.ALL)
        pic = tk.PhotoImage(file = "../resources/lose.gif")
        self._canvas.create_image(0, 0, image = pic, anchor = tk.NW)
        self.root.update()








