from sys import stdin as si
from sys import stdout as so

string = si.readline().rstrip()
result = ""

result += string[0]
for i in range(1, len(string), 1):
    if string[i] == "-":
        result += string[i+1]
print(result)
