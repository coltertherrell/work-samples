#include <iostream>
#include <vector>
#include <string>
#include <stdlib.h> 
#include <time.h>
#include "game.hpp"



GameOfLivestock::GameOfLivestock(std::vector<std::vector<char>> newMap){
	this->map = newMap;
	this->turns = numTurns;
}

void 
GameOfLivestock::printMap(){
	for (std::vector<std::vector<char>>::iterator it_i = this->map.begin(); it_i != this->map.end(); ++it_i){
		for(std::vector<char>::iterator it_ii = it_i.begin(); it_ii != it_i.end(); it_ii++){
			std::cout << it_ii << ' '
		}
		std::cout << '\n'
	}
}

void 
GameOfLivestock::takeTurn(){
	for (std::vector<std::vector<char>>::iterator it_i = this->map.begin(); it_i != this->map.end(); ++it_i){
		for(std::vector<char>::iterator it_ii = it_i.begin(); it_ii != it_i.end(); it_ii++){
			std::cout << "turn \n";
		}
	}
}

int main(){
	int turns = 0;
	int size = 0;
	std::vector<std::vector<char>> gameBoard;
	srand (time(NULL));
	char options[] = {'S','W','H','.'};
	int choice = 0;
	
	//Get User Input
	std::cout << "Enter the game board size: ";
	cin >> size;
	std::cout << '\n';
	
	std::cout << "Enter the number of turns: ";
	cin >> turns;
	std::cout << '\n';
	
	//Build Game Map
	for (int i = 0; i < size; i++){
		vector<char> temp;
		for (int j = 0; j < size; j++){
			choice = rand() % 5;
			temp.push_back(choice);
		}
		gameBoard.push_back(temp);
	}
	
	//Start New Game
	GameOfLivestock game(gameBoard);
	
	for (int k = 0; k < turns; k++){
		std::cout << "Turn " << k << std::endl;
		game.printMap();
		game.takeTurn();
	}
	
	std::cout << "Final Map" << std::endl;
	game.printMap();
}
