CXX = g++ -std=c++11 -Wall -g

%.o : %.c
	$(CXX) -c $<

main: main.o employee.o
	$(CXX) -o $@ main.o employee.o

clean:
	$(RM) main *.o
