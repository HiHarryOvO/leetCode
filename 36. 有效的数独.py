class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        # 注意数独内数字是从1~9而不是0~8
        rows = [[0] * 10 for _ in range(9)]
        cols = [[0] * 10 for _ in range(9)]
        boxes = [[0] * 10 for _ in range(9)]

        for i in range(9):
            for j in range(9):
                if board[i][j] != '.':
                    box = 3 * (i // 3) + (j // 3)
                    num = int(board[i][j])

                    rows[i][num] += 1
                    cols[j][num] += 1
                    boxes[box][num] += 1

                    if rows[i][num] > 1 or cols[j][num] > 1 or boxes[box][num] > 1:
                        return False
        return True
