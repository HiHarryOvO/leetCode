class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        # 二分查找 copied 中第一个大于等于 target 的位置，不存在就返回 len(copied)
        def bin_search(copied, target):
            l, r = 0, len(copied) - 1
            if target > copied[r]:
                return r + 1
            while l < r:
                mid = (l + r) // 2
                if copied[mid] < target:
                    l = mid + 1
                else:
                    r = mid
            return l
        
        n = len(nums1)
        # 创建 nums1 的复制并排序
        copied = nums1[:]
        copied.sort()

        max_ = sum_ = 0

        for i in range(n):
            a, b = nums1[i], nums2[i]
            if a == b:
                continue
            diff = abs(a - b)
            sum_ += diff

            j = bin_search(copied, b)
            # 位置 j 是第一个大于等于 target 的位置
            # 而我们要找的是最接近 target 的值，所以既可能是 j 也可能是 j - 1
            # 注意边界情况
            if j < n:
                max_ = max(max_, diff - (copied[j] - b))
            if j > 0:
                max_ = max(max_, diff - (b - copied[j - 1]))
        
        return (sum_ - max_) % 1000000007
