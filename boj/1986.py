import sys
sys.setrecursionlimit(100000)

n, m = map(int, sys.stdin.readline().split())

possible = [[1 for _ in range(0, m)] for _ in range(0, n)]
place = [[False for _ in range(0, m)] for _ in range(0, n)]

queen_input = list(map(int, sys.stdin.readline().split()))
queen_count = queen_input[0]
queens = []
if (queen_count != 0):
    queen_input = queen_input[1:]
    queens = [[queen_input[2 * i]-1, queen_input[2 * i + 1]-1] for i in range(queen_count)]

knight_input = list(map(int, sys.stdin.readline().split()))
knight_count = knight_input[0]
knights = []
if (knight_count != 0):
    knight_input = knight_input[1:]
    knights = [[knight_input[2 * i]-1, knight_input[2 * i + 1]-1] for i in range(knight_count)]

pawn_input = list(map(int, sys.stdin.readline().split()))
pawn_count = pawn_input[0]
pawns = []
if (pawn_count != 0):
    pawn_input = pawn_input[1:]
    pawns = [[pawn_input[2 * i]-1, pawn_input[2 * i + 1]-1] for i in range(pawn_count)]

QUEEN_DIRECTIONS = [[0, 1], [0, -1], [1, 0], [-1, 0], [1, 1], [-1, -1], [1, -1], [-1, 1]]
KNIGHT_DIRECTIONS = [[1, 2], [1, -2], [-1, 2], [-1, -2], [2, 1], [-2, -1], [2, -1], [-2, 1]]

def queen_move(r, c):
    possible[r][c] = 0
    for DIRECTION in QUEEN_DIRECTIONS:
        nr = r
        nc = c
        while True:
            nr += DIRECTION[0]
            nc += DIRECTION[1]
            if 0 <= nr < n and 0 <= nc < m and place[nr][nc] == False:
                possible[nr][nc] = 0
            else:
                break

def knight_move(r, c):
    possible[r][c] = 0
    for DIRECTION in KNIGHT_DIRECTIONS:
        nr = r + DIRECTION[0]
        nc = c + DIRECTION[1]
        if 0 <= nr < n and 0 <= nc < m:
            possible[nr][nc] = 0
    
for queen in queens:
    place[queen[0]][queen[1]] = True

for knight in knights:
    place[knight[0]][knight[1]] = True

for pawn in pawns:
    place[pawn[0]][pawn[1]] = True
    possible[pawn[0]][pawn[1]] = 0

for queen in queens:
    queen_move(queen[0], queen[1])

for knight in knights:
    knight_move(knight[0], knight[1])

count = 0
for i in possible:
    count += sum(i)

print(count)