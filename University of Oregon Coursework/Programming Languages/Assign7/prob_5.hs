--Sam Vitello
--CIS 425
--Assignment 7 Prob. 5

data State a = State (Int -> (a, Int))

instance Monad State where
    return x = State $ \s -> (x, s)

    (State f) >>= k = State $ \s ->
        let
			(x, s') = f s
			State f' = k x
        in f' s'

get :: State Int
get = State $ \s -> (s, s)

put :: Int -> State ()
put s = State $ \_ -> ((), s)

--(a)
increment :: State ()
increment = do {
				num <- get;
				put (num+1)
				}

--(b)
run :: State () -> Int
run (State f) = do {
					let (a,b) = f;
					b
					}