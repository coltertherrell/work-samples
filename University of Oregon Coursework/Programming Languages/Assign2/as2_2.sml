(*Author: Sam Vitello
	CIS 425
	Assignment 2 Question 2
	
	This function will recursively call until it hits a leaf (base
	case), and then it will return values to be evaluated by function f
	until it has returned all the way to the top.
*)

fun reduce(f,LEAF(a)) = a | reduce(f,NODE(b,c)) = f((reduce(f,b)),(reduce(f,c)));

