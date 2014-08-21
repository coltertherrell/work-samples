import math

def Sieve_Eratost(n):
    """Takes a number n and returns a list of all the primes between 1..n"""
    check_list = list(range(1,n))
    ret_list = list(range(1,n))
    start = 1
    while start < math.sqrt(len(ret_list)):
        start_inx = (ret_list[start])
        for i in range(start_inx,len(check_list)):
            if check_list[i] % start_inx == 0:
                try:
                    ret_list.remove(check_list[i])
                except:
                    pass
        start += 1
    return ret_list


def listSum(n):
    """Takes a list n of type int and returns a sum"""
    sm = 0
    for i in n:
        sm += i
    return sm

lst = Sieve_Eratost(40)
print("Sum of primes to 40: ",listSum(lst))

lst1 = Sieve_Eratost(100)
print(listSum(lst1))
'''
lst2 = Sieve_Eratost(1000000)
print("Sum of primes < 1000000: ",listSum(lst2)) 
'''
