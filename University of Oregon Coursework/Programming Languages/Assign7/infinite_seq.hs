
ones = 1 : ones

intList a = a : intList (a+1)

takeN n _
	| n <= 0 = []
takeN n (x:y) = x : takeN (n-1) y
