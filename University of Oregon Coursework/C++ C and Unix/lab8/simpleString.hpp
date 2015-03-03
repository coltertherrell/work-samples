#ifndef SIMPLESTRING_HPP__
#define SIMPLESTRING_HPP__
#include <string>


class SimpleString {

public:
	SimpleString();
	~SimpleString();

	SimpleString(std::string);

	std::string getStr() const;

	void setStr(std::string);

	SimpleString& operator=(const SimpleString &);
	const SimpleString operator +(const SimpleString &);
	SimpleString& operator ++();
	SimpleString& operator --();
	//SimpleString operator ++(int);
	//SimpleString operator --(int);

	//friend ostream& operator<<(ostream& os, const)
	
private:
	std::string str = "";
};

#endif
