(*Author: Sam Vitello
	CIS 425
	Assignment 2 Question 5

	*)
(*a*)	
datatype Seq = Nil | Cons of int * (unit -> Seq);
fun repeat(x) = Cons(x, fn()=> repeat(x));
val ones = repeat(1);

(*b*)
fun intList n = Cons(n, fn() => intList (n+1));

(*c*)
fun takeN 0 _ = [] | takeN n (Cons (a,f)) = a :: takeN (n-1) (f ());

takeN 4 (intList 10); 