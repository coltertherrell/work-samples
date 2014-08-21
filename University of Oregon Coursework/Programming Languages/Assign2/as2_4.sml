(*Author: Sam Vitello
	CIS 425
	Assignment 2 Question 4

	a) The addition may not run as intended because the value of x.i can either
	be a string or an integer. Thus when the line y = (x.i) + 5; is called
	it may not have the desired result as x.i could be a string. The runtime
	system will catch the problem and spit out an error if any strings are 
	added to ints.
	
	b) Yes the run time system catches the problem. The tag system
	makes it a more useful warning because the compiler knows that there is
	type mismatch as we have defined the tags to be of a certain type.
	It gives the error message "pattern and expression in val dec don't
	agree". This helps because now we know that the value passed is not
	a tag_int.
	*)
	
datatype IntString = tag_int of int|tag_str of string;
val x = tag_str("sup");
let val tag_int (m) = x in m + 5 end;