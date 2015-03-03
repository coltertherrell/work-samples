#include "employee.hpp"
#include <iostream>

int main(){

	Employee brian("Brian","Shaw", 54);
	brian.printEmployeeInfo();

	Employee jill;
	jill.printEmployeeInfo();

	bool check = jill.setAge(-1);
	if (!check){
		std::cout << "Invalid Age" << std::endl;
	}

	jill.printEmployeeInfo();

	jill.setAge(41);
	int q = jill.getAge();
	jill.setFirstName("Jill");
	jill.setLastName("Peters");
	string fname = jill.getFirstName();
	string lname = jill.getLastName();
	std::cout << fname << lname << " is " << q << " years old" << std::endl;


	 
}

