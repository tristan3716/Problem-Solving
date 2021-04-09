from sys import stdin as si
from sys import stdout as so
sum = 0
N = int(si.readline())
numberList = si.readline()
for i in range(N):
    sum += int(numberList[i])
so.write("%d\n" % (sum))
