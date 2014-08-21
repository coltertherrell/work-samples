__author__ = 'Sam'
import Tkinter as tk

class GameDrawable:
    def __init__(self):
        self._picture =  None

    def setSprite(self,image):
        self._picture = tk.PhotoImage(file = image)

    def getSprite(self):
        return self._picture