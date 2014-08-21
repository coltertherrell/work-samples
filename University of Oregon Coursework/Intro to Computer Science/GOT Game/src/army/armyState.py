__author__ = 'Sam'
from Tkinter import PhotoImage

class ArmyState(object):
    def __init__(self, unit):
        super(ArmyState,self).__init__()
        self._unit = unit
        self._power = unit.size

    def getPower(self):
        pass

class NormalState(ArmyState):
    def __init__(self,unit):
        super(NormalState,self).__init__(unit)
        self._power = unit.size
        self.picture = None

    def getPic(self,unit):
        if unit.control == 'user':
            self.picture = unit.setSprite('../resources/starkArmy.gif')
        else:
            self.picture = unit.setSprite('../resources/lanisterArmy.gif')
        return self.picture

    def setSize(self,size):
        return size

    def waitBonus(self):
        return 50


class HeroState(ArmyState):
    def __init__(self,unit):
        super(HeroState,self).__init__(unit)
        self._power = unit.size
        self.picture = None

    def getPic(self,unit):
        if unit.control == 'user':
            self.picture = unit.setSprite('../resources/starkHeroArmy.gif')
        else:
            self.picture = unit.setSprite('../resources/lanisterHeroArmy.gif')


    def setSize(self,size):
        return self._power

    def waitBonus(self):
        return 100