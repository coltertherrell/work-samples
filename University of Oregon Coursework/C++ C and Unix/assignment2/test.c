/*
Author: Sam Vitello
CIS 330 Assignment 2

Runs square1.c, square2.c and diamond.c

*/

#include <stdio.h>
#include "square1.h"
#include "square2.h"
#include "diamond.h"

void clearInputBuffer() {
  while ( getchar() != '\n' );
}

int main(int argc, char** argv){
	printf("%s \n", "Problem 1: Static 10x10 square");
	print10Square();
	printf("\n");
	
	printf("%s \n", "Problem 2: Dynamic nxn square");
	char **square;
	
	int num = 0;
	int numInts=0;
	while (numInts != 1 || num < 2 || num > 10) {
		printf("Please enter the size of the square [2-10]: ");
		numInts = scanf("%d", &num);  
		clearInputBuffer();
	  }

	
	
	
	
	allocateNumberSquare(num,&square);
	initializeNumberSquare(num,square);
	printNumberSquare(num,square);
	//deallocateNumberSquare(num,square);
	printf("\n");
	
	printf("%s \n", "Problem 3: Print nxn diamond");
	printNumberDiamond(num,square);
	deallocateNumberSquare(num,square);
	
}