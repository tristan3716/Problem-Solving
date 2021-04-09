from sys import stdin as si
from sys import stdout as so

N = int(si.readline())
for i in range(0, N, 1):
    A, B = map(int, si.readline().split())
    so.write("%d\n" % (A+B))
