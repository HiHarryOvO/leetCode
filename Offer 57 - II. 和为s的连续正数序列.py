class Solution:
    def findContinuousSequence(self, target: int) -> List[List[int]]:
        ans, sol = [], [1, 2]
        # 使用双指针
        left, right = 1, 2
        while right <= target // 2 + 1:
            currSum = (left + right) * (right - left + 1) / 2
            if currSum == target:
                ans.append(sol[:])
                right += 1
                sol.append(right)
            elif currSum < target:
                right += 1
                sol.append(right)
            else:
                sol.pop(0)
                left += 1
        return ans