

def gcdex(a,b,c):
    b_fact = a//b
    if b % c == 0:
        return [1,b_fact,c]
    c = a%b
    temp = gcdex(b,c,c)
    new_b = temp[0]+temp[1]*b_fact
    return [new_b,temp[0],c]
    
    

print(gcdex(72,57,1))    



59789
