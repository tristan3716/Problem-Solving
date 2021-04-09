import sys

r, c, k = map(int, sys.stdin.readline().split())

visit = [[False for _ in range(0, c)] for _ in range(0, r)]
DIRECTIONS = [[0, 1], [0, -1], [1, 0], [-1, 0]]

arr = []
for i in range(0, r):
    string = sys.stdin.readline().strip()
    arr.append(string)

def dfs(cr, cc, distance):
    if cr == 0 and cc == c - 1:
        if distance == k:
            return 1
        else:
            return 0
    if distance >= k:
        return 0
    
    sum = 0
    for DIRECTION in DIRECTIONS:
        nr = cr + DIRECTION[0]
        nc = cc + DIRECTION[1]
        
        if nr < 0 or nc <0 or nr >= r or nc >= c:
            continue

        if (arr[nr][nc] == 'T'):
            continue

        if not visit[nr][nc]:
            visit[nr][nc] = True
            sum += dfs(nr, nc, distance + 1)
            visit[nr][nc] = False
 
    return sum

visit[r-c][0] = True
count = dfs(r-1, 0, 1)

print(count)