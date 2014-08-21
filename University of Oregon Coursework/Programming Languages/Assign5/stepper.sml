(* Sam Vitello
	CIS 425
	Assignment 5
	Problem 1 
 *)
use "parser.sml";

(* We represent a store as a list of (variable, value) pairs. *)
type store = (ident * int) list;

exception UnboundVariable of ident

(* Read a variable's value from a store. *)
fun get [] x = raise (UnboundVariable x) (* The store is empty *)
(* If the store has at least one pair (y, n), see if the variable matches. *)
  | get ((y, n) :: s) x =
      if x = y
      (* If so, return the value. *)
        then n
        (* Otherwise, look at the rest of the store. *)
        else get s x;

(* Update a store with a new value. s is the original store, x is the name of
 * the variable to assign to, and n is the new value. *)
fun put [] x n = [(x, n)] (* Adding to empty store *)
  | put ((y, m) :: s) x n =
      (* If the store has at least one pair (y, m),
       * see if the variable matches. *)
      if y = x
        (* If so, replace it with the new value. *)
        then (y, n) :: s
        (* Otherwise, look further down the list. *)
        else (y, m) :: put s x n

(* The empty store is simply the empty list. *)
val emptyStore = [];

(* Taking a step either applies reduction rules to produce a new term and a new
 * store or reports that the computation is complete and gives the answer. *)
datatype result = STEP of term * store | DONE of int * store;

exception ToDo

(* This is the core of the stepper. It applies reduction rules to determine
 * how the computation proceeds. If it finds some e' and s' such that
 * <e, s> --> <e', s'>, it should return STEP (e', s'). If e is some integer n,
 * it should return DONE (n, s). It can also happen that an unbound variable
 * (that is, one that's not in the store) is read, in which case UnboundVariable
 * will be raised. *)
fun step e s =
  case e of
       NUM n => DONE(n,s)
     | VAR x => STEP(NUM(get s x),s)
     | ASSIGN (x, e1) => let
				val evaluated = step e1 s
			in
				(case evaluated of
					  DONE (num,store) => DONE(num ,put store x num)
					| STEP (value,store) => STEP(value, store)
					)
			end
     | PLUS (e1, e2) => let
					val x = step e1 s
					val y = step e2 s
				in
					(case x of
						DONE (num,store) =>
							(case y of
								DONE (num1,store1) => DONE(num + num1,store1)
								| STEP (e',s') => STEP(e',s')
								)
						| STEP (e',s') => STEP(e',s')
					)
				end;

fun printStore [] = () (* do nothing *)
  | printStore ((x, n) :: s) = (
      print (x ^ " = " ^ Int.toString n ^ "\n");
      printStore s
    );

fun runStep e s =
  case step e s of
       STEP (e', s') => (
         print (termToString e' ^ "\n");
         printStore s';
         print "Continue? (\"n\" to quit) ";
         (case TextIO.inputLine TextIO.stdIn of
               SOME "n" => print "Goodbye!\n"
             | NONE => () (* no more input; quit *)
             | _ => runStep e' s'
         )
       )
     | DONE (n, s') => (
         print ("Answer: " ^ Int.toString n ^ "\n");
         printStore s'
       );

fun run eStr =
  runStep (parse eStr) emptyStore
  handle SyntaxError msg => print ("Syntax error: " ^ msg)
    | UnboundVariable x => print ("Unbound variable: " ^ x);
