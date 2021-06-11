#模拟边界
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if not matrix or len(matrix) == 0:
            return []
        
        m, n = len(matrix), len(matrix[0])
        left, right, top, bottom = 0, n - 1, 0, m - 1

        ans = []
        while left <= right and top <= bottom:
            for c in range(left, right + 1):
                ans.append(matrix[top][c])
            for r in range(top + 1, bottom + 1):
                ans.append(matrix[r][right])

            if top < bottom and left < right:
                for c in range(right - 1, left - 1, -1):
                    ans.append(matrix[bottom][c])

                for r in range(bottom - 1, top, -1):
                    ans.append(matrix[r][left])

            left += 1
            right -= 1
            top += 1
            bottom -= 1

        return ans

# python简易写法
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        ans = []
        while matrix:
            ans += matrix.pop(0)
            matrix = list(zip(*matrix))[::-1]
        return ans