--Sam Vitello
--CIS 425
--Assignment 7 Prob. 3

--(a)

sift :: Integer -> [Integer] -> [Integer]
sift p [] = []
sift p (n:ns) = if (n `mod` p) == 0 then sift p ns else n : sift p ns 

--(b)
sieve :: [Integer] -> [Integer]
sieve [] = []
sieve (p : ns) = p : sieve (sift p ns)

--(c)
intList a = a : intList (a+1)
takeN n _
	| n <= 0 = []
takeN n (x:y) = x : takeN (n-1) y

primes :: [Integer]
primes = sieve (intList 2)