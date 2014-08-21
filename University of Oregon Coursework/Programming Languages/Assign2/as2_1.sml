(*Author: Sam Vitello
	CIS 425
	Assignment 2 Question 1
	
	The first line of code defines the tree data type. Next I define test function
	t(x) which increments leaf values by 1. The function mapTree takes a 
	function as an argument along with a tree. It returns the LEAF with a new
	value with respect to function f. This is the base case. If a NODE is passed
	to the function it recursively calls itself on the LEAVES or other NODES nested
	in the root node and keeps moving down until the base case is reached.
*)


datatype 'a tree = LEAF of 'a | NODE of 'a tree * 'a tree;
fun t(x) = x + 1;
fun mapTree(f,LEAF(a)) = LEAF(f(a)) | mapTree(f,NODE(b,c)) = NODE((mapTree(f,b)),(mapTree(f,c)));
val test = mapTree(t,NODE(NODE(LEAF 3,LEAF 5),LEAF 8));
test;