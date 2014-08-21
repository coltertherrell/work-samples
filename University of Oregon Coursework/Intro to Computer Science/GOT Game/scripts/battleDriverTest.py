__author__ = 'Sam'
from src.army.Army import *
from src.common.GameWorld import *
from src.common.battleDriver import *
from Graphics.GameCanvas import *
import Tkinter as Tk

world = GameSet()
world.newGame()
canvas = GameCanvas(1325,784,gameworld=world)
world.setGameCanvas(canvas)

army1 = Army(325,210,'user',None,150)
army2 = Army(500,500,'comp',None,200)
goldenTooth = GoldenTooth()
army1.addObserver(goldenTooth)
army2.addObserver(goldenTooth)
goldenTooth.addObserver(army1)
goldenTooth.addObserver(army2)

#Siege Battle Example
#army1.wait()
#army2.move(325,210)

#City Battle Example
army1.move(goldenTooth)
army1.move(goldenTooth)

