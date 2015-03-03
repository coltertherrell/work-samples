/*
*Author: Sam Vitello
*CIS 330 Assignment4
*
*Maze Class. Takes a maze size and then can solve maze using functions.
*/

 
#include <fstream>   
#include "maze.hpp"
#include <iostream>

Maze::Maze(int size) : size(size){};


	
// read maze from file, find starting location
void
Maze::readFromFile(std::ifstream &f){

	//read each line in maze
	for (int i = 0; i<size;i++){
		std::string line;
		f >>  line;
		
		//add char to maze[][] and check for start location
		for (int j = 0; j < size; j++){
			char insert = line[j];
			//std::cout << insert;
			this->maze[i][j]= insert;
			if (insert == 'x'){
				cur_row = i;
				cur_col = j;
				
				//determine starting direction
				if (i == 0){
					this->facing = DOWN;
				}
				else if (i == (size-1)){
					this->facing = UP;
				}
				else if (j == 0){
					this->facing = RIGHT;
				}
				else if (j == (size-1)){
					this->facing = LEFT;
				}
				
			}
		}
		//std::cout << "" << std::endl;
		
	}
	
}

	
// make a single step advancing toward the exit
int
Maze::step(){

	//Facing right
	if (this->facing == RIGHT){
		
		//check if there exists wall on right hand side
		if (this->maze[cur_row +1][cur_col] == '@'){
			
			//free space in front move forward
			if (this->maze[cur_row][cur_col+1] == '.'){
				//std::cout << "RIGHT" << std::endl;
				moveRight();
				return 0;
			}
			//boxed in on right side and direction facing so turn 180 and try to step
			else{
				this->facing = UP;
				return step();
				}
		}
		//no right hand wall exists so must turn to find it again
		else{
			//std::cout << (this->maze[cur_row][cur_col+1]) << std::endl;
			//std::cout << "DOWN" << std::endl;
			this->facing = DOWN;
			moveDown();
			return 3;
		}
	}
	
	//Facing right
	if (this->facing == DOWN){
		
		//check if there exists wall on right hand side
		if (this->maze[cur_row][cur_col-1] == '@'){
			if (this->maze[cur_row+1][cur_col] == '.'){
				//std::cout << "DOWN" << std::endl;
				moveDown();
				return 3;
			}
			//boxed in on right side and direction facing so turn 180 and try to step
			else{
				this->facing = RIGHT;
				return step();
				}
		}
		else{
			//std::cout << "LEFT" << std::endl;
			moveLeft();
			this->facing = LEFT;
			return 1;
		}
	}
	
	//Facing right
	if (this->facing == LEFT){
		
		//check if there exists wall on right hand side
		if (this->maze[cur_row-1][cur_col] == '@'){
			if (this->maze[cur_row][cur_col-1] == '.'){
				//std::cout << "LEFT" << std::endl;
				moveLeft();
				return 1;
			}
			//boxed in on right side and direction facing so turn 180 and try to step
			else{
				this->facing = DOWN;
				return step();
				}
		}
		else{
			//std::cout << "UP" << std::endl;
			moveUp();
			this->facing = UP;
			return 2;
		}
	}
	
	if (this->facing == UP){
		
		//check if there exists wall on right hand side
		if (this->maze[cur_row][cur_col+1] == '@'){
			if (this->maze[cur_row-1][cur_col] == '.'){
				//std::cout << "UP" << std::endl;
				moveUp();
				return 2;
			}
			//boxed in on right side and direction facing so turn 180 and try to step
			else{
				this->facing = LEFT;
				return step();
				}
		}
		else{
			//std::cout << "RIGHT" << std::endl;
			moveRight();
			this->facing = RIGHT;
			return 0;
		}
	}
	else{
		return -1;
	}
}

	
// return true if the maze exit has been reached, false otherwise
bool
Maze::atExit(){
	if (cur_row-1 < 0 || cur_row+1 > ((this->size)-1) || cur_col-1 < 0 || cur_col+1 > ((this->size)-1)){
		return true;
	}
	else{
		return false;
	}
}


// set row and col to current position of 'x'
void
Maze::getCurrentPosition(int &row, int &col){
	row = this->cur_row;
	col = this->cur_col;
}

void
Maze::moveRight(){
	this->cur_col++;
}

void	
Maze::moveLeft(){
	this->cur_col--;
}

void
Maze::moveDown(){
	this->cur_row++;
}

void
Maze::moveUp(){
	this->cur_row--;
}	

/*
int main(int argc, const char *argv[]){
	if( argc != 2 ) //checks for the input file name
	{
		std::cerr << "Error: no input file name" << std::endl;
		std::cerr << "Usage: ./" << argv[0] << " someinput.txt" << std::endl;
		return 1;
	}

	std::ifstream mazeInputFile ( argv[1] );	// open the input file
	int numberOfMazes = 0;
	mazeInputFile >> numberOfMazes;
	
	int mazeSize = 0;
	mazeInputFile >> mazeSize;				// read the maze size from the input file

	std::cout << "size = " << mazeSize << std::endl;
	
	Maze maze(mazeSize);

	// Initialize the maze
	maze.readFromFile(mazeInputFile);
	
	int row, col;
	
	maze.getCurrentPosition(row, col);
	
	do {
		maze.step();
	}
	while( !maze.atExit());
}*/
