from sys import stdin as si
from sys import stdout as so

n = int(si.readline())

for i in range(0, n, 1):
    number = list(map(int, si.readline().split()))
    average = (sum(number) - number[0])/(len(number)-1)
    count = 0
    for j in range(1, len(number), 1):
        if number[j] > average:
            count += 1
    so.write("%.3lf%%\n" % (count*100/(len(number)-1)))
