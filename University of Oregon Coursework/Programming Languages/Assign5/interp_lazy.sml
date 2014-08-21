(*Sam Vitello  
`*CIS 425 
*Assignment s
*Problem 4a
 *)

use "parser_interp.sml";


(* Here is a result datatypes *)
datatype result =
    RES_ERROR of string
  | RES_NUM   of int
  | RES_BOOL  of bool
  | RES_SUCC
  | RES_PRED
  | RES_THUNK of (unit -> result)
  | RES_ISZERO
  | RES_FUN   of (string * term);

(* Here is a basic environment implementation *)
exception not_found;
datatype env = Env of (string * result) list

fun new_env() = Env(nil);
fun extend_env (Env(oldenv), id, value) = Env( (id, value):: oldenv);
fun extend_env_all (Env(oldenv), id_value_list) = Env(id_value_list @ oldenv);
fun lookup_env (Env(nil), id) = (print("Free Var!! "^id); raise not_found)
   |lookup_env (Env((id1,value1)::b), id) =  
        if (id1 = id) 
        then value1
	    else lookup_env(Env(b), id) ;

(*  Here's a partial skeleton of interp : (term * environment) -> result.
    I've done the first case for you
*)
fun interp (exp, env) = 

  case exp of
    AST_ERROR s                 => RES_ERROR s
  | AST_NUM  x                  => RES_NUM x
  | AST_BOOL b                  => RES_BOOL b
  | AST_SUCC                    => RES_SUCC									
  | AST_PRED                    => RES_PRED
  | AST_ISZERO                  => RES_ISZERO
  | AST_IF (exp1, exp2, exp3)   => 	let
										val (RES_BOOL x) = interp(exp1, env)
									in
										case x of
											true => let
												val return = interp(exp2,env)
													in
												return
												end
											| false => let
												val return = interp(exp3,env)
													in
												return
												end
									end
    | AST_APP (exp1, exp2)        => let
										val x = interp(exp1,env)
									in
										case x of 
											RES_FUN (var,exp) => 
											let
												val y = RES_THUNK(fn () => interp(exp2,env))
												val new_env = extend_env (env, var, y)
												val y_1 = interp(exp,new_env)
											in
												y_1
											end
											| RES_SUCC => let
													val y = interp(exp2,env)
													val (RES_NUM q) = y
														in
															RES_NUM (q+1)
														end
											| RES_PRED => let
													val y = interp(exp2,env)
													val (RES_NUM q) = y
														in
															case q of 
																0 => RES_NUM 0
																| _ => RES_NUM (q-1)
															
														end
											| RES_ISZERO => let
												val y = interp(exp2,env)
												val (RES_NUM q) = y
												in
													case q of 
														0 => RES_BOOL true
														| _ => RES_BOOL false
														
												end
											|_ => RES_ERROR "bad type"
											
									end
														
  | AST_ID name                 => let
										val q = lookup_env(env,name)
									in
										case q of
											RES_THUNK(thunk) => thunk()
											|_ => q
									end
  | AST_FUN  (var, exp)         => RES_FUN (var,exp);