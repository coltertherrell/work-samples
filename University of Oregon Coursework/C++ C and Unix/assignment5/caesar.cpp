/*
Author: Sam Vitello
Implements a simple Caesar Cipher based on predefined shift value in caesar.hpp
*/
#include <string>
#include <sstream>
#include <iostream>
#include <cmath>  
#include "caesar.hpp"

		
std::string 
Caesar::encrypt(std::string &text){
//Shift each char in string by private variable shift
	int intBase, newChar;
	char baseChar, convChar;
	std::string enc_str = "";
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
				newChar = (((intBase - 65)+ this->shift) % 26) + 65;
			}
			else if (islower(baseChar)){
			//lower case (note: space included as lower case char)
				newChar = (((intBase - 97) + this->shift) % 27) + 97;
				if (newChar == 123){
				//Set space index to out of range in ASCII
					newChar = 32;
				}
			}
		}
		else if(intBase == 32){
		//Handle spaces
			newChar = ((this->shift-1) %27) + 97;
			if (newChar == 123){
					newChar = 32;
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
Caesar::decrypt(std::string &text){
	std::string original_str = "";
	int intBase, newChar;
	char baseChar, convChar;
	
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
				newChar = (((((intBase - 65) - this->shift) % 26) + 26)%26) + 65;
			}
			else if (islower(baseChar)){
			//lower case (note: space included as lower case char)
				newChar = (((((intBase - 97) - this->shift) % 27) + 27)%27) + 97;
				if (newChar == 123 || newChar == 96){
				//Set space index to out of range in ASCII
					newChar = 32;
				}
			}
		}
		else if(intBase == 32){
		//Handle spaces
			newChar = ((((27-this->shift -1) %27)+27)%27) + 97;
			if (newChar == 123){
					newChar = 32;
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
