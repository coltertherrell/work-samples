#include <iostream>
#include <string>
#include "simpleString.hpp"

SimpleString::SimpleString(){
	this->str = "";
}

SimpleString::SimpleString(std::string newStr){
	this->str = newStr;
}

void
SimpleString::setStr(std::string str){
	this->str = str;
}

std::string
SimpleString::getStr() const{
	return this->str;
}

SimpleString&
SimpleString::operator=(const SimpleString& other){
	this->str = other.getStr();
	return *this;
}

const SimpleString 
SimpleString::operator +(const SimpleString & other){
	this->str.append(other.getStr());
	return *this;
}
SimpleString& 
SimpleString::operator ++(){
	this->str.push_back('*');
	return *this;
}
SimpleString& 
SimpleString::operator --(){
	this->str.pop_back();
	return *this;
}
//SimpleString operator ++(int);
//SimpleString operator --(int);
