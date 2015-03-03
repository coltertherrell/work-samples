/*
Author: Sam Vitello
Compiled using gcc 4.8.2
CIS 330 University of Oregon
Assignment 1

Game where player competes against the computer. Object of the game
is not to be stuck taking the last stick.
*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>      
#include <string.h>

int getHumanChoice() {
    int num;
	
	printf("Enter a number between 1 and 3: ");
	scanf("%d",&num);
	while (num > 3 || num < 1){
		printf("Cheater! Enter a number between 1 and 3: ");
		scanf("%d",&num);
	}
	return num;
}

int getComputerChoice(int current_sticks) {

	if (current_sticks == 4){
		return 3;
	}
	else if(current_sticks == 3){
		return 2;
	}
	else if(current_sticks == 2){
		return 1;
	}
	else if (current_sticks == 7){
		return 2;
	}
	else if (current_sticks == 6){
		return 1;
	}
	else if (current_sticks == 8){
		return 3;
	}
	else{
    
		/* get a pseudo-random integer between 1 and 3 (inclusive) */
		int rand_choice = rand() % 3 + 1;
    
		if (rand_choice > current_sticks) return current_sticks;
    
		return rand_choice;
	}
}


int main(int argc, char** argv) 
{
    int human, computer, number_sticks;
    
    
    srand (time(NULL)); /* for reproducible results, you can call srand(1); */
    
    printf("Welcome to the game of sticks!\n");
    printf("How many sticks are there on the table initially (10-100)? ");
    scanf("%d", &number_sticks);
    
	 while (number_sticks < 10 || number_sticks > 100){
		printf("How many sticks are there on the table initially (10-100)? \n");
		scanf("%d", &number_sticks);
		
	 }
	 printf("\n");
	 
	
	 char result[256];
	 char comp_choice[256];
	 
	 while (number_sticks > 0){
		snprintf(result, sizeof result, "%s%d%s", "There are ",number_sticks," sticks left.");
		printf("%s \n", result);
		
		human = getHumanChoice();
		number_sticks = number_sticks - human;
		if (number_sticks <= 0){
			printf("You Lose");
			return 0;
		}
		printf("\n");
		
		snprintf(result, sizeof result, "%s%d%s", "There are ",number_sticks," sticks left.");
		printf("%s \n", result);
		computer = getComputerChoice(number_sticks);
		snprintf(comp_choice, sizeof comp_choice, "%s%d%s", "Computer Selects ",computer," sticks");
		printf("%s \n", comp_choice);
		printf("\n");
		number_sticks = number_sticks - computer;
		if (number_sticks <= 0){
			printf("You Win");
			return 0;
		}
	 
	 }
    
    return 0;
}

