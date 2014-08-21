import math

def little_big(h,g,m):
    g_inv = (g**(m-2)) % m
    l1 = []
    l2 = []
    floor = int(math.sqrt(m) + 1)
    for i in range(0,floor+1):
        l1.append(g**i % m)
        l2.append(h*(g_inv**(floor*i)) %m)

    for i in range(0,len(l1)):
        for j in range(0,len(l2)):
            if l1[i] == l2[j]:
                print(i,j)
                return i + floor*j

g_1 = 14597855949940500403
h_1 = 6217597677504589256
p   = 18446744073709558081
print(little_big(h_1,g_1,p))

'''
>>> g_2
9770858417974093901
>>> g_1
12858995283014395317
>>> n
18446744073709558080
>>> g_3
2161837708449834929
>>> g_4
16566954555927684742
>>> g_5
13536826006816050787
>>> g_6
11498050613625529692
>>> g_7
8631129481341256421

h_1 = 945606088422804101
h_2 = fast_pwr(h,(n/(3**2)),n+1)

p = 18446744073709558081
'''
