__author__ = 'Sam'

from Graphics.GameCanvas import *
from src.common.GameWorld import *
from src.army.Army import *
import time
from src.army.armyDecorators import *

gameWorld = GameSet()
canvas = GameCanvas()
gameWorld.newGame()
gameWorld.setGameCanvas(canvas)

playerStart = Army(540,10,'user',100)
compStart = EasyDecorator(Army(1050,490,'comp',100))
#compStart = NormalDecorator(Army(1050,490,'comp',100))             ####DECORATOR PATTERN####
#compStart = HardDecorator(Army(1050,490,'comp',100))
gameWorld.addArmy(playerStart)
gameWorld.addArmy(compStart)
canvas.redraw(gameWorld._drawable)






tk.mainloop()
