#include <iostream>
#include "simplestring.hpp"

int main(){
	SimpleString str1;
	int test1 = str1.getLength();
	std::cout << "Length of default: " << test1 << std::endl;

	char * test = new char[4];
	test[0] = 't';
	test[1] = 'e';
	test[2] = 's';
	test[3] = 't';
	SimpleString *str2 = new SimpleString(test, 4);
	char * test2 = str2.getStr();
	std::cout << "new string: " << test2  << std::endl;
}
