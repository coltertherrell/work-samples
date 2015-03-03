/*
Author: Sam Vitello
CIS 330 Assignment 2

Prints diamond shaped representation of an nxn array.

*/

#include <stdio.h>
#include <stdlib.h>

void printNumberDiamond(const int size, char **square){
	int mid = (size/2);
	int row = 1;
	int to_print = -1;
	int start_index;
	int count = 0;
	
	
		if (size%2 == 0){
			while (row <= size){
				//printf("Row: %d \n",row);
				if (row < mid){
					to_print += 2;
					start_index = mid - (row);
				}
				else if (row > (mid+1)){
					to_print -= 2;
					start_index = (row) - mid -1;
				}
				else if(row == mid || row == (mid+1)){
					to_print = size-1;
					start_index = 0;
				}
				
				for (int i = 0; i < size; i++){
					if (i < start_index || i >= start_index+to_print){
						printf("  ");
					}
					else if (i >= start_index && i < start_index+to_print){
						printf("%c ", square[row-1][count]);
						count++;
					}
				
				}
				printf("\n");
				row++;
				count = 0;
			}
		}else{
			while(row <= size){
				if (row <= mid+1){
					to_print += 2;
					start_index = mid - (row-1);
				}
				else if (row > mid+1){
					to_print -= 2;
					start_index = (row-1) - mid;
				}
				
				for (int i = 0; i < size; i++){
					if (i < start_index || i > start_index+to_print){
						printf("  ");
					}
					else if (i >= start_index && i < start_index+to_print){
						printf("%c ", square[row-1][count]);
						count++;
					}
				
				}
				printf("\n");
				row++;
				count = 0;
			}
		}
		

}