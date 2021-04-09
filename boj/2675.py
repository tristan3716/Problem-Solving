from sys import stdin as si
from sys import stdout as so

S = int(si.readline())

for j in range(S):
    R, string = si.readline().rstrip().split()
    R = int(R)
    for i in range(len(string)):
        for k in range(R):
            so.write(string[i])
    so.write("\n")
