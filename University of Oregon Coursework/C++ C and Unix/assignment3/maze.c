/*
Author: Sam Vitello
CIS 330 Winter 2015
Assignment 3

This program uses a .txt representation of mazes and solves them by following the right hand wall through the maze until an
exit is found. Sample input:

7  //total number of cases
5  //row
&&x&&
&...&
&...&
&.&.&
&&&x&

...

Output:
A set of directions (relative to an over head observer) to solve the maze

Sample Output:
DOWN
RIGHT
DOWN
DOWN
DOWN
DOWN
*/


#include <stdio.h>
#include <stdbool.h>
#define MAX_SIZE 30

enum Direction { DOWN, RIGHT, UP, LEFT }; //might be useful

void mazeSolve(char maze[][MAX_SIZE], const int mazeSize, int cur_x, int cur_y, enum Direction facing ){
	//recursively searches a maze using a technique where the right hand is always following the wall
	 //if found exit, return
	 //iter = iter--;
	 if (cur_x > (mazeSize-1) || cur_y > (mazeSize-1) || cur_x < 0 || cur_y < 0){
		return;
	 }
	 
	 //if facing right
	 if (facing == RIGHT){
		if (maze[cur_y +1][cur_x] == '@'){
			if (maze[cur_y][cur_x+1] == '.'){
				printf("%s \n", "RIGHT");
				mazeSolve(maze,mazeSize,cur_x+1,cur_y,RIGHT);
			}
			else{
				mazeSolve(maze,mazeSize,cur_x,cur_y,UP);
			}
		}
		else{
			printf("%s \n", "DOWN");
			mazeSolve(maze, mazeSize,cur_x, cur_y+1, DOWN);
		}
	 }
	 
	 //if facing down
	 else if(facing == DOWN){
		if (maze[cur_y][cur_x-1] == '@'){
		//if right side touching wall
		
			if (maze[cur_y+1][cur_x] == '.'){
				//if we can move down again do so
				printf("%s \n", "DOWN");
				mazeSolve(maze,mazeSize,cur_x,cur_y+1,DOWN);
			}
			else{
				//if we cant try moving right
				mazeSolve(maze,mazeSize,cur_x,cur_y,RIGHT);
			}
		}
		else{
		//no longer a wall on right side => we must move left
			printf("%s \n", "LEFT");
			mazeSolve(maze, mazeSize,cur_x-1, cur_y, LEFT);
		}
	 }
	 
	 //if facing up
	 else if(facing == UP){
		if (maze[cur_y][cur_x+1] == '@'){
			if (maze[cur_y-1][cur_x] == '.'){
				//printf("%c \n", maze[cur_y-1][cur_x]);
				printf("%s \n", "UP");
				mazeSolve(maze,mazeSize,cur_x,cur_y-1,UP);
			}
			else{
				//printf("%c \n", maze[cur_y-1][cur_x]);
				//printf("%s \n","LEFT STAY");
				mazeSolve(maze,mazeSize,cur_x,cur_y,LEFT);
			}
		}
		else{
			//printf("%c \n", maze[cur_y-1][cur_x]);
			printf("%s \n", "RIGHT");
			mazeSolve(maze, mazeSize,cur_x+1, cur_y, RIGHT);
		}
	 }
	
	//facing left
	else if(facing == LEFT){
		if (maze[cur_y -1][cur_x] == '@'){
			if (maze[cur_y][cur_x-1] == '.'){
				printf("%s \n", "LEFT");
				mazeSolve(maze,mazeSize,cur_x-1,cur_y,LEFT);
			}
			else{
				mazeSolve(maze,mazeSize,cur_x,cur_y,DOWN);
			}
		}
		else{
			printf("%s \n", "UP");
			mazeSolve(maze, mazeSize,cur_x, cur_y-1, UP);
		}
	 }
	 else{
		printf("%s \n","NO DIRECTION");
	 }
	
}

int main( int argc, const char* argv[] ){
    if( argc != 2 ) { 
        //checks for the input file name
        printf( "error; no input file name\n" );
        return 1;
    }

    FILE *filePointer;
    filePointer = fopen( argv[1], "r" );

    //declare array 
    char maze[MAX_SIZE][MAX_SIZE] = { 0 };

    int numberOfTestCases = 0;
    fscanf( filePointer, "%d\n", &numberOfTestCases );

    //Loop through test cases in test file
    for( int testCaseNumber = 0; testCaseNumber < numberOfTestCases; testCaseNumber++ ) {
        int mazeSize = 0;
        fscanf( filePointer, "%d\n", &mazeSize );
		
	//initialize maze array
	//char maze[mazeSize][mazeSize];
        
	//initialize line array  and start indicies
        printf( "ENTER\n" );
	char line[mazeSize];
	int start_x=0;
	int start_y=0;
	
	//populate maze array
       	for (int i = 0; i<mazeSize; i++){
			fscanf(filePointer, "%s",line);
			for (int j = 0; j<mazeSize; j++){
				maze[i][j] = line[j]; 
				char test = maze[i][j];
				//printf("%c", test);
				if (test == 'x'){
					//printf("%s \n", "In loop");
					start_x = j;
					start_y = i;
				} 
			}
			//printf("\n");
		}

	//determine start direction
	enum Direction start_dir;
	if (start_x == 0){
		start_dir = RIGHT;
		//printf("%s \n","RIGHT");
	}
	else if (start_x == (mazeSize-1)){
		start_dir = LEFT;
		//printf("%s \n","LEFT");
	}
	else if (start_y == 0){
		start_dir = DOWN;
		//printf("%s \n","DOWN");
	}
	else if (start_y == (mazeSize-1)){
		start_dir = UP;
		//printf("%s \n","UP");
	}
	else {
		printf("%s \n", "Invalid Map");
		return 0;
	}
	
	//printf("%s \n","In mazeSolver");
	//printf("%c \n", maze[start_y-1][start_x]);
	
	//call maze solver
	mazeSolve(maze, mazeSize, start_x, start_y, start_dir);
    printf( "EXIT\n***\n" );        
    }
    fclose( filePointer );
    return 0;
}
	
