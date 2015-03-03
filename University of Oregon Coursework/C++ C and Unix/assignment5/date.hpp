/*
*Author:Sam Vitello
*
*/

#ifndef DATE_HPP_
#define DATE_HPP_

#include <string>
#include "cipher.hpp"

class Date:public Cipher {
	public:
		Date() {}
		~Date() {}
		
		std::string encrypt(std::string &text);
		
		std::string decrypt(std::string &text);
	
	private:
		int shiftDate = 110892;
};

#endif
