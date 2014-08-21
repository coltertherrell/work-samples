(*  This file defines a recursive-descent parser that builds an abstract
    syntax tree for an expression in a lambda-calculus with addition and
    mutable state. Two versions are provided: parseFile f reads the expression
    from a file named f, while parse s reads the expression directly from the
    string s.

    Originally created by Jon Riecke. Later modified by Jay Sachs,
    Kim Bruce, Barbara Lerner, Geoffrey Smith, and Luke Maurer.
*)

(*  Increase print depth so abstract syntax trees get displayed completely.  *)
Control.Print.printDepth:= 100;

type ident = string;

datatype term = NUM of int
              | VAR of ident
              | ASSIGN of ident * term
              | PLUS of term * term
              (* | FUN of ident * term
              | APP of term * term *);

exception SyntaxError of string;

signature PARSER =
sig
  val parse : string -> term
  val parseFile : string -> term
end

structure Parser : PARSER =
struct
  datatype token = T_ID of string
                 | T_NUM of int 
                 | T_EQ
                 | T_PLUS
                 (* | T_FN *)
                 | T_LPAREN
                 | T_RPAREN
                 (* | T_FNARROW *)
                 | T_EOF

  (*  nexttoken recognizes reserved words, along with
      identifiers, integer literals, and the symbols =, =>, +, (, and ).
      Also, # begins a comment that extends to the end of the current line.
  *)
  fun nexttoken strm =
    case TextIO.input1 strm of
        NONE   => T_EOF
      | SOME c =>
          if Char.isSpace c then
            nexttoken strm 
          else if Char.isAlpha c then
            let
              fun getid id =
                case TextIO.lookahead strm of
                     NONE   => id
                   | SOME d =>
                       if Char.isAlpha d orelse Char.isDigit d then
                         (TextIO.input1 strm; getid (id ^ str d))
                       else
                         id
              val ident = getid (str c)
            in case ident of
                (* "fn"     => T_FN
              |*) _        => T_ID ident
            end
          else if Char.isDigit c then
            let
              fun getnum num =
                case TextIO.lookahead strm of
                    NONE   => num
                  | SOME d =>
                      if Char.isDigit d then
                        (TextIO.input1 strm; getnum (10*num + ord d - ord #"0"))
                      else
                        num
            in
              T_NUM (getnum (ord c - ord #"0"))
            end
          else
            case c of
                #"=" => (case TextIO.lookahead strm of
                    (* SOME #">" => (TextIO.input1 strm; T_FNARROW)
                   |*) _         => T_EQ)
              | #"(" => T_LPAREN
              | #")" => T_RPAREN
              | #"+" => T_PLUS
              | #"#" =>
                  (*  A # starts a comment, so we eat the current line.  *)
                  let fun eatline () =
                    case TextIO.input1 strm of
                        NONE       => T_EOF
                      | SOME #"\n" => nexttoken strm
                      | SOME _     => eatline ()
                  in
                    eatline ()
                  end
              | _    => (print ("Skipping illegal character " ^ str c ^ ".\n");
                 nexttoken strm)

  fun gettokens strm =
    let
      fun gettokens_aux toks =
        let val tok = nexttoken strm
        in
          if tok = T_EOF then
            (TextIO.closeIn strm; rev (T_EOF::toks))
          else
            gettokens_aux (tok::toks)
        end
    in
      gettokens_aux []
    end

  fun lex str = gettokens (TextIO.openString str)

  fun lexFile file = gettokens (TextIO.openIn file)
  (*  Note that we could instead have called
        explode (input (openIn file))
      to get a list of all the characters in the file.
  *)

  (*  startsExp tok tests whether tok is in FIRST(Exp).  *)
  fun startsExp (T_ID s)  = true
    | startsExp (T_NUM n) = true
    (* | startsExp T_FN      = true *)
    | startsExp T_LPAREN  = true
    | startsExp _         = false

  fun level T_PLUS = SOME 2
    | level T_EQ = SOME 3
    | level t = if startsExp t then SOME 1 else NONE

  (* 
   * Exp0 ::= x | n | fn x => Exp3 | ( Exp3 )
   * Exp1 ::= Exp0 | Exp0 Exp1
   * Exp2 ::= Exp1 | Exp1 + Exp2
   * Exp3 ::= Exp2 | x = Exp3
   *)
  fun parseExp p [T_EOF] = raise SyntaxError "Unexpected EOF"
    | parseExp 0 (T_ID v::tail)    = (VAR v, tail)
    | parseExp 0 (T_NUM n::tail)   = (NUM n, tail)
    (*| parseExp 0 (T_FN::T_ID v::T_FNARROW::tail) =
        let val (ast, rest) = parseExp 3 tail
        in
          (FUN(v, ast), rest)
        end
    | parseExp 0 (T_FN::T_ID v::tail) =
        raise SyntaxError ("Missing => after fn " ^ v)
    | parseExp 0 (T_FN::tail) =
        raise SyntaxError "Missing identifier after fn" *)
    | parseExp 0 (T_LPAREN::tail) =
        let val (ast, rest) = parseExp 3 tail
        in
          if hd rest = T_RPAREN then
            (ast, tl rest)
          else
            raise SyntaxError "Missing )"
        end
    | parseExp 1 tokens =
        let
          val (ast, rest) = parseExp 0 tokens
          fun parseExp_1_aux ast rest =
            (* if startsExp (hd rest) then
              let
                val (ast', rest') = parseExp 0 rest
              in
                parseExp_1_aux (APP(ast, ast')) rest'
              end
            else *)
              (ast, rest)
        in
          parseExp_1_aux ast rest
        end
    | parseExp 2 tokens =
        let
          val (ast, rest) = parseExp 1 tokens
          fun parseExp_2_aux ast rest =
            if hd rest = T_PLUS then
              let
                val (ast', rest') = parseExp 1 (tl rest)
              in
                parseExp_2_aux (PLUS(ast, ast')) rest'
              end
            else
              (ast, rest)
        in
          parseExp_2_aux ast rest
        end
    | parseExp 3 (T_ID x::T_EQ::tail) =
        let
          val (ast, rest) = parseExp 3 tail
        in
          (ASSIGN(x, ast), rest)
        end
    | parseExp 3 tokens =
        parseExp 2 tokens
    | parseExp p _ = 
        raise SyntaxError "Bad expression"

  fun parseToks tokens =
    let val (ast, rest) = parseExp 3 tokens
    in
      if hd rest = T_EOF then
        ast
      else
        raise SyntaxError "EOF expected"
    end

  fun parse s = parseToks (lex s)
  fun parseFile f = parseToks (lexFile f)
end;

signature PRINTER =
sig
  val termToString : term -> string
end;

structure Printer : PRINTER =
struct
  (* Works the way Haskell generates instances of the Show class. *)
  fun parens b s = if b then "(" ^ s ^ ")" else s

  fun showPrec _ (NUM n) = Int.toString n
    | showPrec _ (VAR x) = x
    | showPrec p (ASSIGN (x, m)) = parens (p < 3) (x ^ " = " ^ showPrec 3 m)
    | showPrec p (PLUS (m, n)) = parens (p < 2)
        (showPrec 2 m ^ " + " ^ showPrec 1 n)
    (*| showPrec p (FUN (x, m)) = parens (p < 3)
        ("fn " ^ x ^ " => " ^ showPrec 3 m)
    | showPrec p (APP (m, n)) = parens (p < 1)
        (showPrec 1 m ^ " " ^ showPrec 0 n) *)

  val termToString = showPrec 3
end;

open Parser;
open Printer;
