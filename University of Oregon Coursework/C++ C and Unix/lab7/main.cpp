#include <iostream>
#include <random>
#include <vector>
#include <algorithm>

void printVector(const std::vector<int> &v){
	std::vector<int>::const_iterator it;

	for(it=v.begin() ; it < v.end(); it++){
		std::cout << *it << std::endl;

	}

}

int main(){
	std::vector<int> v;
	std::default_random_engine generator;
	std::uniform_int_distribution<int> distribution(0,100);
       
	for (int i = 0; i < 15; i++){
		//int newRand = distribution(generator);
		v.push_back(distribution(generator));
	}
	
	std::cout << "Original Vector" << std::endl;
	printVector(v);

	std::sort(v.begin(), v.end());

	std::cout << "\n" << "Sorted Vector" << std::endl; 
	printVector(v);
	
	std::vector<int> vCopy (15);
	std::copy(v.begin(), v.end(),vCopy.begin());

	std::cout << "\n" << "V Copy" << std::endl;
	printVector(vCopy);

	std::random_shuffle(vCopy.begin(), vCopy.end());
	std::cout << "\n" << "Shuffled Copy" << std::endl;
	printVector(vCopy);
}



