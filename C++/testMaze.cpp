/*
*Author: Sam Vitello
*CIS 330 Assignment 4
*
*Main function to run maze.cpp
*/
#include <iostream> 
#include <fstream>   
#include "maze.hpp"
//#include "utils.hpp"

enum Direction { RIGHT, LEFT, UP, DOWN };

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
	
	for (int currentMaze = 0; currentMaze < numberOfMazes; currentMaze++ ){
		
		int mazeSize = 0;
		mazeInputFile >> mazeSize;				// read the maze size from the input file

		//std::cout << "size = " << mazeSize << std::endl;
		
		Maze maze(mazeSize);

		// Initialize the maze
		maze.readFromFile(mazeInputFile);
		
		int row, col;
		
		maze.getCurrentPosition(row, col);
		int nextStep = -1;
		std::string toPrint;
		std::cout << "ENTER" << std::endl;
		do {
			nextStep = maze.step();
			if (nextStep == 0){
				toPrint = "RIGHT";
			}
			else if (nextStep == 1){
				toPrint = "LEFT";
			}
			else if (nextStep == 2){
				toPrint = "UP";
			}
			else if (nextStep == 3){
				toPrint = "DOWN";
			}
			else{
				std::cout << "Error: Step returned invalid output" << std::endl;
				break;
			}
			std::cout << toPrint << std::endl;
		}
		while( !maze.atExit());
		std::cout << "EXIT" << std::endl;
		std::cout <<  "-----------" << std::endl;
	}
}