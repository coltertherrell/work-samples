#!/bin/bash +x
./maze maze_input.txt > maze_output.txt
diff -u output.txt maze_output.txt
