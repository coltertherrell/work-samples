--Sam Vitello
--CIS 425
--Assignmnet 7 Prob. 4

import Data.Char

--(a)
--It is impossible to write such a function. This is because if a function takes
--an IO variable as an argument it must return something of type IO. The >>=
--funtion takes an IO of type a and returns and IO of type b. It is impossible
--to simply return something of type b.

--(b)
strip :: String -> String

strip [] = []
strip (s:st) = if isSpace s || isPunctuation s then strip st else toLower s : strip st 

palindrome = do {
				putStrLn "Enter some text";
				inp <- getLine;
				return ((strip inp) == reverse (strip inp))
				}
	
--(c)
forever :: IO () -> IO ()
forever a = do {
			action <- a;
			return action;
			forever a
			}

--(d)
repeatN :: Int -> IO () -> IO ()
repeatN 0 a = do {
				ret <- putChar '\n';
				return ret
				}
repeatN n a = do {
				rep <- a;
				return rep;
				repeatN (n-1) a
				}

--(e)

sequences :: [IO a] -> IO [a]
sequences [] = do {
					return []
				}
sequences (a:as) = do {
					first <- a;
					next <- sequences as;
					return (first : next)
					}


	