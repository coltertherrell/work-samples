__author__ = 'Sam'
from random import randint
from src.common.GameWorld import *

def simpleBattle(army,city):
    #print "Simple Battle Commencing"
    armyPWR = army.size
    cityPWR = city.defense + city.importanceBehavior.importance()
                                                            ###STRATEGY PATTERN####
    #print city.defense
    #print city.importanceBehavior.importance()

    total = randint(0,(armyPWR+cityPWR))
    winner = city
    loser = army
    if total > cityPWR:
        winner = army
        loser = city
        city.control = army
        winner.size = winner.powerState.setSize(total - cityPWR)
        #print city.name,"has been taken!"
        #print winner.name,"now has ",winner.size,"troops left"
    else:
        army.size = 0
        #print loser.name,"was unable to take the city"

def siegeBattle(army1,army2,city):
    #print "Siege Battle Commencing"
    if army1.control == army2.control:
        raise TypeError("Armies are on the same team")
    if city.control == army1:
        defender = army1
        attacker = army2
    else:
        defender = army2
        attacker = army1
    defensePWR = defender.size + city.defense + city.importanceBehavior.importance()
    attackPWR = attacker.size
    total = randint(0,(defensePWR+attackPWR))
    winner = defender
    loser = attacker
    if total > defensePWR:
        winner = attacker
        loser = defender
        winner.size = winner.powerState.setSize(total-loser.size)
        loser.size = 0
        #print loser.name,"has been defeated, ",city.name,"has been taken!"
        #print winner.name, "now has ", winner.size, "troops left"
        city.control = winner
    else:
        loser.size = 0
        winner.size = winner.powerState.setSize(total)
        #print loser.name,"has been defeated"
        #print winner.name, "now has ", winner.size, "troops left"

def cityBattle(army,city):
    #print "City Battle Commencing"
    armyPWR = army.size
    cityPWR = city.defense
    total = randint(0,(armyPWR+cityPWR))
    winner = city
    loser = army
    if total > cityPWR:
        winner = army
        loser = city
        city.control = army
        winner.size = winner.powerState.setSize(total - cityPWR)
        #print city.name,"has been taken!"
        #print winner.name,"now has ",winner.size,"troops left"
    else:
        army.size = 0
        #print loser.name,"was unable to take the city"






