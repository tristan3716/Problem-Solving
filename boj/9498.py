from sys import stdin as si
from sys import stdout as so

score = int(si.readline())
if score >= 90:
    so.write("A\n")
elif score >= 80:
    so.write("B\n")
elif score >= 70:
    so.write("C\n")
elif score >= 60:
    so.write("D\n")
else:
    so.write("F\n")
