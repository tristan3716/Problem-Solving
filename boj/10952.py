from sys import stdin as si
from sys import stdout as so

while 1:
    A, B = map(int, si.readline().split())
    if A == 0 and B == 0:
        break
    so.write("%d\n" % (A+B))
