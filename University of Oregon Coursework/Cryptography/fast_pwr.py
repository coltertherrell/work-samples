import math

def fast_pwr(m,n, mod):
    temp = n
    pwr_list = []
    while temp > 0:
        test = int(math.log(temp,2))
        pwr_list.append(2**test)
        temp = temp - (2**test)
    pwr_list.sort()
    
    q = 2
    prev = m % mod
    pwrs = []
    while q <= pwr_list[-1]:
        a = prev**2 % mod
        if q in pwr_list:
            pwrs.append(a)
        prev = a
        q = q*2
    prod = 1
    for i in pwrs:
        prod*=i
    if 1 in pwr_list:
        prod*=m
    return prod % mod
    

print(fast_pwr(6466570642616143345,288230376151711845,18446744073709558081))

'''
7**((18446744073709558080 // 64)) % 18446744073709558081
'''
