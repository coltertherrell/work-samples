CXX = g++ -std=c++11 -Wall -g

%.o : %.c 
	$(CXX) -c $< 

testMaze: maze.o testMaze.o
	$(CXX) -o $@ testMaze.o maze.o

main: maze.o utils.o main.o
	$(CXX) -o $@ main.o utils.o maze.o

clean:
	$(RM) main testMaze *.o
