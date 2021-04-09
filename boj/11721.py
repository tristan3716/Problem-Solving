from sys import stdin as si
from sys import stdout as so

pos = 0
string = si.readline()
while pos < len(string):
    so.write("%c" % string[pos])
    pos += 1
    if pos % 10 == 0:
        so.write("\n")
