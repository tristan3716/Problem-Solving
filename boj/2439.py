N = int(input())
for i in range(0, N, 1):
    for j in range(i, N-1, 1):
        print(" ", end="")
    for j in range(0, i+1, 1):
        print("*", end="")
    print("")
