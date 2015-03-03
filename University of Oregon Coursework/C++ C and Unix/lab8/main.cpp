#include <iostream>
#include <string>
#include "simpleString.hpp"

int main(){
	SimpleString *test = new SimpleString("hello");
	std::string getTest = test->getStr();
	std::cout << getTest  << std::endl;

	SimpleString *test2 = new SimpleString("world");
	test = test + test2;
	getTest = test->getStr();
	std::cout << getTest <<std::endl;
}
