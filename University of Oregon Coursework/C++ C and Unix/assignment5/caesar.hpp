/*
*Author:Sam Vitello
*
*/

#ifndef CAESAR_HPP_
#define CAESAR_HPP_

#include <string>
#include "cipher.hpp"

class Caesar:public Cipher {
	public:
		Caesar() {}
		~Caesar() {}
		
		std::string encrypt(std::string &text);
		
		std::string decrypt(std::string &text);
	
	private:
		int shift = 12;
};

#endif
