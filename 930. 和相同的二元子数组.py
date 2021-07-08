# 解法1：前缀和 + 哈希表
class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        total, res = 0, 0
        count = collections.Counter()

        for num in nums:
            # 先记录，再加num
            count[total] += 1
            total += num
            res += count[total - goal]

        return res

class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        n, res = len(nums), 0
        sum1 = sum2 = 0
        left1 = left2 = right = 0
        while right < n:
            sum1 += nums[right]
            while left1 <= right and sum1 > goal:
                sum1 -= nums[left1]
                left1 += 1

            sum2 += nums[right]
            while left2 <= right and sum2 >= goal:
                sum2 -= nums[left2]
                left2 += 1

            res += left2 - left1
            right += 1

        return res