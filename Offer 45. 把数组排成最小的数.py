class Solution:
    def minNumber(self, nums: List[int]) -> str:
        strs = [str(num) for num in nums]

        def quick_sort(l, r):
            if l >= r:
                return

            i, j = l, r
            while i < j:
                # 先从右边开始
                # 注意等于时也需要移动，因为可能存在重复
                while strs[j] + strs[l] >= strs[l] + strs[j] and i < j: # 注意i < j还需要判断
                    j -= 1
                while strs[i] + strs[l] <= strs[l] + strs[i] and i < j:
                    i += 1
                strs[i], strs[j] = strs[j], strs[i]
            strs[i], strs[l] = strs[l], strs[i]

            quick_sort(l, i - 1)
            quick_sort(i + 1, r)

        quick_sort(0, len(strs) - 1)
        return "".join(strs)