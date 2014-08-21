def fast_power(a,n):
    result = 1
    value = a
    power = n
    while power > 0:
        if power % 2 == 1:
            result = result * value
        value = value * value
        power = power/2
    return result

print(fast_power(7,314159) % 2013)
