#include <stdio.h>
#include <stdlib.h>

void allocateNumberSquare(const int size, char ***square){
	int i;
	(*square) =(char **)malloc(size * sizeof(char *));
	for(i = 0; i < size; i++){
		(*square)[i]=(char *)malloc(size*sizeof(char));
	}
}

void initializeNumberSquare(const int size, char **square){
	for (int i = 0; i < size; i++){
		for (int j = 0; j < size; j++){
			square[i][j] = ('0' + j);
		}
	}
}

void printNumberSquare(const int size, char **square){
	for (int i = 0; i < size; i++){
		for (int j = 0; j < size; j++){
			if (j == (size-1)){
				printf("%c \n", square[i][j]);
			}
			else{
				printf("%c ", square[i][j]);
			}
		}
	}
}

void deallocateNumberSquare(const int size, char **square){
	for(int i = 0; i < size; i++){
		free(square[i]);
	}
	free(square);
}