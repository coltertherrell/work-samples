#include <sstream>     	// std::istringstream
#include <fstream>
#include <map>		// std::multimap
#include <algorithm>   	// std::copy, std::for_each
#include <climits>
#include "mr.hpp"
#include "ioutils.hpp"
#include "sentenceStats.hpp"

namespace mr{

void
SentenceStats::MRmap(const std::map<std::string,std::string> &input,
				std::multimap<std::string,int> &out_values) {
		IOUtils io;
				
		for (auto it = input.begin(); it != input.end(); it++ ) {
		std::string inputString = io.readFromFile(it->first);

		// Split up the input into words
		std::istringstream iss(inputString);
		
		do {
			std::string sentence = "";
			std::string word;
			iss >> word;
			int numWords = 0;
			std::string lastChar = "";
			do {
				numWords++;
				sentence += word; 
				lastChar = word.at(word.length()-1);
				iss >> word;
			} while (iss&&(lastChar != "." || lastChar != "?" || lastChar != "!"));
			// Each word gets assigned a count of 1
			out_values.insert(std::pair<std::string,int>(sentence,numWords));
		} while (iss);
	}
}

void
SentenceStats::MRreduce(const std::multimap<std::string,int> &intermediate_values,
					std::map<std::string,int> &out_values) {

	// Sum up the counts for all intermediate_values with the same key
	// The result is the out_values map with each unique word as
	// the key and a total count of occurrences of that word as the value.
	int totalSentences = 0;
	int totalWords = 0;
	int curSentence = 0;
	int maxSentence = INT_MIN;
	int minSentence = INT_MAX;
	std::for_each(intermediate_values.begin(), intermediate_values.end(),
			// Anonymous function that increments the sum for each unique key (word)
			[&](std::pair<std::string,int> mapElement)->void
			{
				curSentence = mapElement.second;
				if (curSentence > maxSentence){
					maxSentence = curSentence;
				}
				else if (curSentence < minSentence){
					minSentence = curSentence;
				}
				totalWords += curSentence;
				totalSentences += 1;
			});  // end of for_each
	
	int avg = totalWords/totalSentences;
	out_values["Maximum Sentence Length: "] = maxSentence;
	out_values["Minimum Sentence Length: "] = minSentence;
	out_values["Average Sentence Length: "] = avg;
	
}

};
