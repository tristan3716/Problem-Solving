from sys import stdin as si
from sys import stdout as so

A, B, C = map(int, si.readline().split())

if A < B:
    if B > C:
        if A > C:
            so.write("%d\n" % A)
        else:
            so.write("%d\n" % C)
    else:
        so.write("%d\n" % B)
else:
    if B < C:
        if A > C:
            so.write("%d\n" % C)
        else:
            so.write("%d\n" % A)
    else:
        so.write("%d\n" % B)
