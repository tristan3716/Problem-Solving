/**
 * @title 스도쿠 G4
 * @see https://www.acmicpc.net/problem/2239
 * @since 2019.12.11
 * @category back-tracking
 * @description
 *      그냥... 백 트래킹...
 */

def print_grid(arr):  # O(R*9) don't care
    for yi in range(9):
        for xi in range(9):
            print(arr[yi][xi], end='')
        print()


def is_empty_location(arr, cx, cy):  # O(1)
    if arr[cy][cx] is 0:
        return True
    else:
        return False


def is_last_location(cx, cy):  # O(1)
    if cy is 9:
        return True
    else:
        return False


def used_in_col(num, available_col):  # O(1)
    return available_col[num-1] is True


def used_in_box(arr, row, col, num):  # O(3^2)
    for i in range(3):
        for j in range(3):
            if arr[i + row][j + col] == num:
                return True
    return False


def is_available_location(arr, row, col, num, available_col):  # O(9 + 1)
    return \
        used_in_col(num, available_col) \
        and not used_in_box(arr, row - row % 3, col - col % 3, num)


def get_next_location(cx, cy):
    if (cx+1) % 9 is 0:
        return 0, cy+1
    else:
        return cx+1, cy


def solve_sudoku(arr, cx, cy, _available_rows, _available_cols):
    row = cy
    col = cx

    if is_last_location(col, row):
        return True

    nx, ny = get_next_location(col, row)

    if is_empty_location(arr, col, row):
        for i in range(0, len(_available_rows[cy])):
            num = _available_rows[cy][i]
            if num is not -1:
                temp_num = _available_rows[cy][i]
                _available_rows[cy][i] = -1

                if is_available_location(arr, row, col, num, _available_cols[col]):
                    _available_cols[cx][num-1] = False
                    arr[row][col] = num
                    if solve_sudoku(arr, nx, ny, _available_rows, _available_cols):
                        return True
                    arr[row][col] = 0
                    _available_cols[cx][num-1] = True
                _available_rows[cy][i] = temp_num
    else:  # skip this location(cx, cy)
        return solve_sudoku(arr, nx, ny, _available_rows, _available_cols)

    return False


if __name__ == "__main__":
    # string = input()
    #string = "693700010400000000020000000000050407008000300001090000300400200050100000000806000"

    #input_list = list(map(int, string))

    grid = []
    for i in range(0, 9, 1):
        temp = input()
        temp = list(map(int, temp))
        grid.append(temp)

    temp_available_rows = [[True]*9 for _ in range(9)]
    available_cols = [[True]*9 for _ in range(9)]

    for i in range(len(grid)):
        for j in range(len(grid[i])):
            num = grid[i][j]
            if num is not 0:
                temp_available_rows[i][num-1] = False
                available_cols[j][num-1] = False

    available_rows = []
    for i in range(9):
        temp_row = []
        temp_col = []
        for j in range(9):
            if temp_available_rows[i][j] is True:
                temp_row.append(j+1)
        available_rows.append(temp_row)
    if not solve_sudoku(grid, 0, 0, available_rows, available_cols):
        print("No solution exists")
    else:
        print_grid(grid)
