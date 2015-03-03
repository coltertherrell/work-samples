#include "employee.hpp"
#include <iostream>
#include <string>

//using namespace std;


Employee::Employee(){
	std::cout << "In Default Constructor" << std::endl;
	this->age = 0;
	this->fname = "John";
	this->lname = "Doe";
}

Employee::Employee( std::string fname, std::string lname, int age) {
	std::cout << "In Regular Constructor" << std::endl;
	this->age = age;
	this->fname = fname;
	this->lname = lname;
}

bool
Employee::setAge(int age){
	if (age < 0 || age > 150){
		return false;
	}
	else{
		this->age = age;
		return true;
	}
}


int
Employee::getAge(){
	return this->age;
}

bool
Employee::setFirstName(string name){
	this->fname = name;
	return true;
}


string
Employee::getFirstName(){
	return this->fname;
}

bool
Employee::setLastName(string name){
	this->lname = name;
	return true;
}

string
Employee::getLastName(){
	return this->lname;
}

void
Employee::printEmployeeInfo(){
	std::cout << "Employee First Name: " << this->fname << std::endl;
	std::cout << "Employee Last Name: " << this->lname << std::endl;
	std::cout << "Employee Age " << this->age << std::endl;
}
