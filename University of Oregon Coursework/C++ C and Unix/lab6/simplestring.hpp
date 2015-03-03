#ifndef SIMPLESTRING_HPP__
#define SIMPLESTRING_HPP__

#include <string>

class SimpleString {

	public:
		SimpleString();
		~SimpleString();

		SimpleString(char * string, int length);

		SimpleString(const SimpleString& other);
		
		char * getStr() const;

		int getLength() const;		

	private:
		int length;
		char * str;

};

#endif
