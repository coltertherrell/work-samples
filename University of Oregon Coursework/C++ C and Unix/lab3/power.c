#include <stdio.h>

void power(double a, double b){
	double ret = 1;
	while (b > 0){
		ret = ret*a;
		b--;
	}
	printf("%f \n", ret);
}
