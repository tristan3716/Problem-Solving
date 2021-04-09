from sys import stdin as si
from sys import stdout as so

N, X = map(int, si.readline().split())
nList = si.readline().split()

for i in range(N):
    if int(nList[i]) < X:
        so.write("%d\n" % int(nList[i]))
