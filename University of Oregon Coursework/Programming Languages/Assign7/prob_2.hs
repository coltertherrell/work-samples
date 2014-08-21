--Sam Vitello
--CIS 425
--Assignment 7 Prob. 2

--(a)
  
evens :: [Int]
evens = 2 : [x+2 | x <- evens] 

odds :: [Int]
odds = 1 : [x+2 | x <- odds]

--(b)

merge :: [Int] -> [Int] -> [Int]
merge (x:xs) [] = x:xs
merge [] (y:ys) = y:ys
merge [] [] = []
merge (x:xs) (y:ys) = x:y:merge xs ys

--merge evens odds will not terminate as evens and odds are both infinite lists.
--There is no case in the merge function to handle infinite lists so it will
--will continue merging infinitely until the list eats up all the memory.
--
--Similarly length (merge evens odds) will not terminate. As it is lazy evaluation
--if we have this call in a function it will not necessarily loop infinitely
--until we actually call length and it must evaluate its argument, but a straight
--call of length (merge evens odds) will not terminate.

--(c)

--i.
intList a = a : intList (a+1)
cubed = 0 : [x*x*x | x <- (intList 1)]

--ii.
trips = 1 : [3*x | x <- trips]

--iii.
sqrd = 0 : [x*x | x <- (intList 1)]
inter_weave = merge (intList 0) sqrd

--iv
neg = -1 : [(x-1) | x <- neg]
