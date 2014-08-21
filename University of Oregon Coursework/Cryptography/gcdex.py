def GCDEX(a,b):
    a_prime = a//b
    b_prime = a % b
    if b % b_prime == 0:
        return [a_prime,1]
    working = GCDEX(b,b_prime)
    return [working[0]*a_prime + working[1],working[0]]

t = (1948079324977600*2115219245686386)
print(GCDEX(2064864717109742544859348292473,t))

    
