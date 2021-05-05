# 方法1
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        maxVal = max(nums)
        dp = [0] * (maxVal + 1)

        for num in nums:
            dp[num] += num

        def rob(arr):
            first, second = arr[0], max(arr[0], arr[1])
            for i in range(2, len(arr)):
                first, second = second, max(second, first + arr[i])
            return second

        return rob(dp)


# 方法2
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        def rob(arr) -> int:
            # 注意：处理特殊情况
            if len(arr) == 1:
                return arr[0]

            first, second = arr[0], max(arr[0], arr[1])
            for i in range(2, len(arr)):
                first, second = second, max(second, first + arr[i])
            return second

        # 排序
        nums.sort()
        ans = 0
        sums = [nums[0]]

        for i in range(1, len(nums)):
            if nums[i] == nums[i - 1]:
                sums[-1] += nums[i]
            elif nums[i] == nums[i - 1] + 1:
                sums.append(nums[i])
            else:
                # 出现间隔大于1
                ans += rob(sums)
                sums = [nums[i]]
        # 最后还要计算一次
        ans += rob(sums)
        return ans