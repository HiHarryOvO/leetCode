class Solution:
    def rob(self, nums: List[int]) -> int:
		# 定义一个固定起点和终点范围的函数
        def robRange(start: int, end: int) -> int:
            first = nums[start]
            second = max(nums[start], nums[start + 1])
            for i in range(start + 2, end + 1):
                first, second = second, max(second, first + nums[i])
            return second
        
        n = len(nums)
		# 边界情况
        if n == 1:
            return nums[0]
        elif n == 2:
            return max(nums[0], nums[1])
        else:
			# 比较不打劫第一家和打劫第一家两种情况
            return max(robRange(0, n - 2), robRange(1, n - 1))