class Solution:
    def maxSumMinProduct(self, nums: List[int]) -> int:
        # 创建前缀和数组
        n = len(nums)
        sum_arr = [0] * (n + 1)

        for i in range(n):
            sum_arr[i + 1] = sum_arr[i] + nums[i]

        # 注意这里左右边界是有默认值的，例如全局最小值，在左右都找不到边界，则应该是最大边界
        left, right = [0] * n, [n - 1] * n
        # 单调栈
        s = []

        for i, num in enumerate(nums):
            # 注意这里是大于等于
            # 这里表示i位置的数小于等于s[-1]位置的数，因此更新s[-1]位置的右边界
            while s and nums[s[-1]] >= num:
                right[s[-1]] = i - 1
                s.pop()
            # 这里表示i位置的数小于s[-1]位置的数（大于等于的已经全部被弹出），因此更新i位置的左边界
            if s:
                left[i] = s[-1] + 1
            s.append(i)

        # 求最大值
        best = max([(sum_arr[right[i] + 1] - sum_arr[left[i]]) * num for i, num in enumerate(nums)])
        mod = 10 ** 9 + 7
        return best % mod