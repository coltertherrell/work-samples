#include <sstream>     	// std::istringstream
#include <fstream>
#include <map>		// std::multimap
#include <algorithm>   	// std::copy, std::for_each
#include "mr.hpp"
#include "ioutils.hpp"
#include "phrases.hpp"


namespace mr {
// Map and reduce methods to count occurrences of a word in a given text.

// A specialized map function with string keys and int values
void
Phrases::MRmap(const std::map<std::string,std::string> &input,
				std::multimap<std::string,int> &out_values) {
	IOUtils io;
	// input: in a real Map Reduce application the input will also be a map
	// where the key is a file name or URL, and the value is the document's contents.
	// Here we just take the string input and process it.
	for (auto it = input.begin(); it != input.end(); it++ ) {
		std::string inputString = io.readFromFile(it->first);

		// Split up the input into words
		std::istringstream iss(inputString);
		int wordLength = 0;
		int wordStart = 0;
		std::string firstWord;
		iss >> firstWord;
		int startBegin = 0;
		int startLength = firstWord.length()-1;
		std::string phrase = "";
		
		while (startLength > 0 &&((int)firstWord.at(startLength) > 122 || (int)firstWord.at(startLength) < 65)){
				startLength--;
			}
		while (startBegin < startLength &&((int)firstWord.at(startBegin) > 122 || (int)firstWord.at(startBegin) < 65)){
				startBegin++;
			}
			//std::cout << wordStart << std::endl;
		startLength = (startLength-startBegin);
		firstWord = firstWord.substr(startBegin,startLength+1);
		
		while (iss) {
			std::string secondWord;
			iss >> secondWord;
			wordLength = secondWord.length()-1;
			wordStart = 0;
			while (wordLength > 0 &&((int)secondWord.at(wordLength) > 122 || (int)secondWord.at(wordLength) < 65)){
				wordLength--;
			}
			while (wordStart < wordLength &&((int)secondWord.at(wordStart) > 122 || (int)secondWord.at(wordStart) < 65)){
				wordStart++;
			}
			//std::cout << wordStart << std::endl;
			wordLength = (wordLength - wordStart);
			secondWord = secondWord.substr(wordStart,wordLength+1);
			
			phrase = firstWord + " " + secondWord;
			firstWord = secondWord;
			// Each word gets assigned a count of 1
			out_values.insert(std::pair<std::string,int>(phrase,1));
		}
	}
}

// A specialized reduce function with string keys and int values
void
Phrases::MRreduce(const std::multimap<std::string,int> &intermediate_values,
					std::map<std::string,int> &out_values) {

	// Sum up the counts for all intermediate_values with the same key
	// The result is the out_values map with each unique word as
	// the key and a total count of occurrences of that word as the value.
	std::for_each(intermediate_values.begin(), intermediate_values.end(),
			// Anonymous function that increments the sum for each unique key (word)
			[&](std::pair<std::string,int> mapElement)->void
			{
				out_values[mapElement.first] += 1;
			});  // end of for_each
}

}; // namespace mr
