#ifndef __GAME_HPP_
#define __GAME_HPP_

#include <iostream>
#include <vector>
#include <string>

class GameOfLivestock(){

public:
	GameOfLivestock(std::vector<std::vector<char>>);
	~GameOfLivestock();
	
	void printMap();
	
	void takeTurn();

private:
	std::vector<std::vector<char>> map;

};

int main();