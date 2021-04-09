from sys import stdin as si
from sys import stdout as so

while True:
    string = si.readline().rstrip()
    if string == "END":
        break
    string = string[::-1]
    so.write("%s\n" % string)
