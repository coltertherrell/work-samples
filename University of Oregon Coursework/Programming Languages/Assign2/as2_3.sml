(*Author: Sam Vitello
	CIS 425
	Assignment 2 Question 3
	
	b) The reason that these two calls return the function passed as an argument
	is because uncurry returns the initial form of the input of curry and vice-versa.
	So for example when uncurry(curry f); is called first f, defined as a function
	that takes two ints as an argument and returns an int, is passed to curry,
	which which returns f as a function that instead passes the second argument
	in a function call which then returns the result. This is the input for uncurry
	which takes this format and changes it back to the original format of an 
	arguemnt of two ints that goes to another int. Thus the input is the same 
	as the output.
*)

fun curry f x y = f(x,y);
fun uncurry g(x,y) = g x y;

fun f(x,y) = x+y;
fun g x y = (x+y);


uncurry(curry f);
curry(uncurry(g));