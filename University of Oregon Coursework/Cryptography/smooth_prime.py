from fast_pwr import *
from random import randint
from gcdex import GCD

def miller_rabin(n,size,num):
    if n%2 == 0:
        return False
    q = n-1
    while q%2 == 0:
        q = q//2
    for i in range(0,num):
        test = randint(2,n)
        if GCD(n,test) > 1:
            return False
        b = 1
        while b < size**2:
            if fast_pwr(test,b*q,n) == 1:
                return True
            b *= 2
    return False

def Sieve(q):
    check = list(range(2,q+1))
    start = 0
    while start < q:
        try:
            check[start]
        except:
            return check
        n = 2
        test = check[start]*n
        while test <= check[-1]:
            if test in check:
                check.remove(test)
            n += 1
            test = check[start]*n
        start += 1
    return check

def smooth(n,b):
    smooth = n
    test_list = Sieve(b)
    for prime in test_list:
        if smooth == 1:
            return True
        while smooth % prime == 0:
            smooth = smooth // prime
    if smooth == 1:
        return True
    else:
        return False
            
def main():
    start = (2**49) + 1
    while start < 2**50:
        check1 = miller_rabin(start,50,50)
        if check1:
            check2 = smooth(start-1,1000)
            if check2:
                return start
        start += 2
    return "Cannot find it"

def test_main():
    q = 1
    start = (2**50)+1
    while q ==1:
        if miller_rabin(start,50,50):
            return start
        else:
            start += 2
        

print(main())

'''print(main())
answer 562949953444897
'''
