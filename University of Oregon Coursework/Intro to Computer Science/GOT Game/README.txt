Author: Sam Vitello

The Battle For Kings Landing:

The goal of my game is to move an army from Riverrun to Kings Landing. The Player starts as the Red army on the top
while the computer is the yellow army at the bottom of the screen. Every turn a player has the options to
either move or wait. There are buttons on the bottom of the screen that allow the player to make these selections.
After the selection to move or wait has been made the computer will make a move and both the computer and player
will be given gold for the turn. The more cities a player controls the more gold they will accumulate every turn.
When an army is defeated it will reset to its home city (Riverrun or Kings Landing) and the new army size will be
the amount of Gold the player had when their army died. Each player has another option which is to upgrade the army.
This costs 500 Gold. An upgraded army will not lose troops when it is victorious in taking a city so it will be easier
to make a run with a particular army if it is upgraded. Waiting gives a normal army a 50 troop bonus while it will
give an upgraded army a 100 troop bonus.

Run game using run.py

Source Code:
Dependencies: Python 2.7

Patterns:

Strategy: The strategy pattern was used to make certain cities harder to take depending on their relative importance.
The strategy pattern can be found in src/common/cities.py. The different importanceBehaviors can be seen at the top of
the file and each individual city instance below declares a specific importanceBehavior. To see where this goes into
the battles look at src/common/battleDriver.py. In both simpleBattle and siegeBattle the cityPWR/defensePWR includes
the importanceBehavior of the city. (To check that these are in fact different or to see battles more in depth
uncomment the commented out print statements)

Observer: The observer pattern is used in my project to notify the game canvas when a player army has been reset
so that it can redraw the army back at its starting location. The main observer classes can be found in
src/common/observer.py. In src/army/Army.py you can see that Armies are observable and observers. You can also find an
instance of the observer pattern in the setState() method.To see where the observers are added look in
src/common/GameWorld.py in the addArmy() method. The last line of this method adds the game
canvas as an observer of each added army. Also in GameWorld.py you can see where the canvas in notified in the
resetArmy() method directly below the addArmy() method. Finally to see what happens when the Armies update the canvas
look in Graphics/GameCanvas.py update() method.

Decorator: The decorator pattern is used in my game to toggle the difficulty of the computer player. In order to do
this in run.py file there are three different computer starting army options. (Choose one). The main code for the
decorator pattern can be found in src/army/armyDecorators.py. The only method that gets decorated is the reset method.
The computer player gets more (or less) troops when the army is reset depending on the difficulty level. You can see
where the armies are reset in src/common/GameWorld.py in the resetArmy() method.

State: The state pattern was used to upgrade the armies to a hero army, which gains more troops by waiting and
doesn't lose troops when it takes cities. It also changes the picture of an upgraded army. The state setter can be
found in src/army/Army.py in the Army setState() method. The main code for the state pattern can be found in
src/army/armyState.py. The code that upgrades an army can be found in Graphics/GameCanvas.py in the upgrade() method

Visitor: The visitor pattern is used whenever an army moves. The Army 'visits' the city and then initializes a
battle depending on factors such as the cities controller and if the enemy army is currently occupying the city.
The base visitor pattern classes can be found in src/common/Visitor.py. The main code for the visitor pattern
can be found in Graphics/GameCanvas.py in the visit() method and in the moveArmy() method. In src/common/cities.py you
can find the accept() method.

General Thoughts: To run the game use run.py (not in any directory)