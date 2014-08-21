

def gaus_elim_RREF(matrix):
    cols = len(matrix[0]) 
    rows = len(matrix)
    cur = 0
    pivot_cnt = 0
    for i in range(0,rows):
        for j in range(0,cols):
            if matrix[i][j] = 1:
                for k in range(i+1,rows)
                    if matrix[k][j] == 1:
                        
                pivot_cnt += 1
                cur += 1
                continue


















def main():
    var_smooth = int(input("enter B: "))
    var_numbers = int(input("enter number of vectors: "))
    var_instructions = input("enter 'next' to go to next vector: (push enter)")
    count = 0
    matrix = []
    for i in range(0,var_smooth):
        row = []
        for i in range(0,var_numbers):
            row.append(0)
        matrix.append(row)
    print("------Vector 1 ------")
    while count < var_numbers:
        inpt = input("enter factor: ")
        inpt_ex = int(input("enter exponent: "))
        while inpt != "next":
            matrix[int(inpt)-1][int(count)] = (inpt_ex % 2)
            inpt = (input("enter factor: "))
            if inpt != "next":
                inpt_ex = int(input("enter exponent: "))
            else:
                print("------Vector ",count+2,"------")
        count += 1
    print(len(matrix))

main()
