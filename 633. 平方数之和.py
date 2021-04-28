class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        if c == 0:
            return True

        left, right = 0, int(c ** 0.5)
        while left <= right:
            curr_sum = left * left + right * right
            if curr_sum == c:
                return True
            elif curr_sum > c:
                right -= 1
            else:
                left += 1 
        return False
            