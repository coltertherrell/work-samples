/*
Author: Sam Vitello
Implements a Date Cipher based on predefined shift value in date.hpp
*/
#include <string>
#include <sstream>
#include <iostream>
#include <sstream>
#include "date.hpp"

		
std::string 
Date::encrypt(std::string &text){
//Shift each char in string by private variable shift
	int intBase, newChar;
	char baseChar, convChar;
	
	//Get date as a string
	std::ostringstream os;
	os << this->shiftDate;
	std::string strDate = os.str();
	int dateSize = strDate.length();
	
	//encryption string
	std::string enc_str = "";
	
	int dateCount = 0;
	//std::cout << "\n" << "shift key: " << this->shift << "\n";
	for (int i = 0; i< text.length(); i++){
		
		baseChar = text.at(i);
		intBase = (int) baseChar;
		//std::cout << "base char: " << baseChar << std::endl;
		//std::cout << "base int: " << intBase << std::endl;
		if (isalpha(baseChar)){
		//if its a letter
			
			//std::cout << " has value " << intBase;
			if (isupper(baseChar)){
			//upper case
				newChar = (((intBase - 65)+ ((int)strDate.at(dateCount % dateSize) - 48)) % 26) + 65;
				dateCount++;
			}
			else if (islower(baseChar)){
			//lower case (note: space included as lower case char)
				newChar = (((intBase - 97) + ((int)strDate.at(dateCount % dateSize)) - 48) % 26) + 97;
				dateCount++;
			}
		}
		else{
		//If not letter or space return as is
			newChar = intBase;
		}
		
		//std::cout << " has shifted value: " << newChar;
		char convChar = (char)newChar;
		//std::cout << " has char value: " << convChar << std::endl;
		enc_str += convChar;
	}
	return enc_str;
}

std::string 
Date::decrypt(std::string &text){
	std::string original_str = "";
	int intBase, newChar;
	char baseChar, convChar;
	
	//Get date as a string
	std::ostringstream os;
	os << this->shiftDate;
	std::string strDate = os.str();
	int dateSize = strDate.length();
	
	int dateCount = 0;
	//std::cout << "\n" << "shift key: " << this->shift << "\n";
	for (int i = 0; i< text.length(); i++){
		
		baseChar = text.at(i);
		//std::cout << "base char: " << baseChar;
		intBase = (int) baseChar;
		if (isalpha(baseChar)){
		//if its a letter
			
			//std::cout << " has value " << intBase;
			if (isupper(baseChar)){
			//upper case
				/*if (i == 0){
					std::cout << intBase - 65 << std::endl;
					std::cout << (int)strDate.at(dateCount % dateSize) << std::endl;
					std::cout <<((intBase - 65) - (((int)strDate.at(dateCount % dateSize)) - 48)) <<std::endl;
				}*/
				newChar = (((((intBase - 65) - (((int)strDate.at(dateCount % dateSize)) - 48)) % 26) + 26)%26) + 65;
				dateCount++;
			}
			else if (islower(baseChar)){
			//lower case (note: space included as lower case char)
				newChar = (((((intBase - 97) - (((int)strDate.at(dateCount % dateSize)) - 48)) % 26) + 26)%26) + 97;
				dateCount++;
			}
		}

		else{
		//If not letter or space return as is
			newChar = intBase;
		}
		
		//std::cout << " has shifted value: " << newChar;
		char convChar = (char)newChar;
		//std::cout << " has char value: " << convChar << std::endl;
		original_str += convChar;
	}
	return original_str;
}
