#include <iostream>
#include <string>
#include "simplestring.hpp"

SimpleString::SimpleString() {
	this->str = NULL;
	this->length = 0;
}

SimpleString::SimpleString(char * string, int lgth){
	this->str = string;
	this->length = lgth;
}

SimpleString::SimpleString(const SimpleString& other){
	this->str = other.getStr();
	this->length = other.getLength();
}

SimpleString::~SimpleString(){
	delete[] this->str;
}

char *
SimpleString::getStr() const{
	return this->str;
}

int
SimpleString::getLength() const{
	return this->length;
}
