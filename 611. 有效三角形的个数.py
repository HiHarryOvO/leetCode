class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        # 边界情况
        n = len(nums)
        if n < 3:
            return 0

        nums.sort()
        ans = 0

        # target 到最后一个下标为 2
        for target in range(n - 1, 1, -1):
            left, right = 0, target - 1
            while left < right:
                if nums[left] + nums[right] > nums[target]:
                    # 将 right - left 加入最终答案
                    ans += right - left
                    right -= 1
                else:
                    left += 1
        
        return ans