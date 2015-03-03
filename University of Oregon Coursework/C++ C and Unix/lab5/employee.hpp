#ifndef MAZE_HPP_
#define MAZE_HPP_

#include <iostream>
#include <string>
using namespace std;

class Employee
{
public:
	Employee();
	~Employee() {}

	Employee(string fname, string lname, int age);

	bool setAge(int age);

	int getAge();

	bool setFirstName(string name);

	string getFirstName();

	bool setLastName(string name);

	string getLastName();

	void printEmployeeInfo();

private:
	int age;
	string fname;
	string lname;

};

#endif 
