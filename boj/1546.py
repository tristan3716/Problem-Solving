from sys import stdin as si
from sys import stdout as so

n = int(si.readline())
score = list(map(float, si.readline().split()))

biggest = max(score)
for i in range(n):
    score[i] = score[i] / biggest * 100

so.write("%lf\n" % (sum(score)/n))
