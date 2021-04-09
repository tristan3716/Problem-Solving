from sys import stdin as si
from sys import stdout as so

n = si.readline().rstrip()
alpha = [-1 for i in range(26)]

for i in range(len(n)):
    index = ord(n[i]) - 97
    if alpha[index] == -1:
        alpha[index] = i

for i in range(len(alpha)):
    so.write("%d " % alpha[i])
so.write("\n")
