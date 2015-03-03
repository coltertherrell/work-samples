#include <stdio.h>
#include <stdbool.h>
#define ROW 3
#define COL 4

bool arrayEqual(int a[ROW][COL], int b[ROW][COL], int m, int n){
	
	for (int i = 0; i < m; i++){
		for (int j = 0; j < n; j++ ){
			if (a[i][j] != b[i][j]){
				return false;
			}
		}
	}
	return true;

}

int main(int argc, const char * argv[]){

	int a[ROW][COL] = {
		{0,1,2,3},
		{4,-1,6,7},
		{8,9,10,11}
	};

	int b[ROW][COL] = {
		{0,1,2,3},
		{4,5,6,7},
		{8,9,10,11}
	};

	
	bool result = arrayEqual(a,b,3,4);
	printf("%s",result ? "true" : "false");
	return 0;
}
