# 双指针，O(n)
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)
        res, total = 10 ** 6, 0
        left, right = 0, 0
        while right < n:
            while total < target and right < n:
                total += nums[right]
                right += 1
            if total >= target:
                while total >= target:
                    total -= nums[left]
                    left += 1
                res = min(res, right - left + 1)
        return res if res != 10 ** 6 else 0


# 前缀和 + 二分查找，O(n log n)
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        def binSearch(left, right, k):
            start = left
            # 出现等于号 <==> 无论匹配到哪种情况，边界都要缩小
            while left <= right:
                mid = (left + right) // 2
                if preSum[mid + 1] - preSum[start] >= k:
                    right = mid - 1
                else:
                    left = mid + 1
            return left
        
        if not nums:
            return 0
        n = len(nums)
        preSum = [0] * (n + 1)
        for i in range(0, n):
            preSum[i + 1] = preSum[i] + nums[i]
        
        res = 10 ** 6
        for i in range(n):
            ind = binSearch(i, n - 1, target)
            if ind != n and preSum[ind + 1] - preSum[i] >= target:
                res = min(res, ind - i + 1)
        return res if res != 10 ** 6 else 0