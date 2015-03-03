/*
 * maze.hpp
 *      Author: norris
 */

#ifndef MAZE_HPP_
#define MAZE_HPP_

#include <iostream>
#include <fstream>

class Maze
{
public:
	Maze(int size);
	~Maze() {}

	enum Direction { DOWN, RIGHT, UP, LEFT };

	// Implement the following functions:

	// read maze from file, find starting location
	void readFromFile(std::ifstream &f);

	// make a single step advancing toward the exit
	int step();

	// return true if the maze exit has been reached, false otherwise
	bool atExit();

	// set row and col to current position of 'x'
	void getCurrentPosition(int &row, int &col);
	
	// move current position
	void moveRight();
	
	void moveLeft();
	
	void moveUp();
	
	void moveDown();
	
private:
	char maze[30][30] = { /* */ };
	int cur_row = -1;
	int cur_col = -1;
	enum Direction facing = RIGHT;
	int size;
};

//int main(int argc, const char *argv[]);


#endif /* MAZE_HPP_ */
