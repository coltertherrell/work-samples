from fast_pwr import fast_pwr
from gcdex import *

def ElGamal_Decrypt(p,a, g_b, m):
    '''Takes shared prime p, secret key a, g_b and encrpyted message
        m from Bob and returns unencrypted message'''
    secret = fast_pwr(g_b,a,p)
    secret_inv = GCDEX(p,secret)[0]
    return (m*secret_inv) % p

print(ElGamal_Decrypt(1125899906848799,955362250684418,783126311904636, 104670539972472))
    
'''
p: 61
g: 2

Alice:
a: 52
g_a = 56

Bob:
b: 16
g_b = 22


Key = 56
encrypt:
message: 512
(22, 28672)
'''

