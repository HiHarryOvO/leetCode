class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        mp = {0: -1}
        count, max_len = 0, 0
        for i, num in enumerate(nums):
            count += 1 if num == 1 else -1
            if count in mp.keys():
                max_len = max(max_len, i - mp[count])
            else:
                mp[count] = i
        return max_len