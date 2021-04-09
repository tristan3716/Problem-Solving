from sys import stdin as si
from sys import stdout as so

ESM = list(map(int, si.readline().split()))
calESM = [1, 1, 1]

count = 1
while 1:
    if ESM == calESM:
        break
    calESM[0] += 1
    calESM[1] += 1
    calESM[2] += 1
    if calESM[0] > 15:
        calESM[0] = 1
    if calESM[1] > 28:
        calESM[1] = 1
    if calESM[2] > 19:
        calESM[2] = 1
    count += 1
so.write("%d\n" % count)