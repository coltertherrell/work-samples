(*Author: Sam Vitello
CIS 425 Assignment 4 Problem 7.2
*)

fun mult(a,b) = 
	let
		val count = ref a
		val result = ref 0
	in
		while !count > 0 do
			(result := !result+b;
			count := !count - 1);
		!result
	end;