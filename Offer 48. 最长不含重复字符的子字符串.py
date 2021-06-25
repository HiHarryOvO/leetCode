# DP + hash map
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        dic = {}
        res = tmp = 0
        for j, ch in enumerate(s):
            i = dic.get(ch, -1)
            dic[ch] = j

            tmp = tmp + 1 if tmp < j - i else j - i
            res = max(res, tmp)

        return res

# 双指针 + hash map
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        dic = {}
        res, i = 0, -1
        for j, ch in enumerate(s):
            if ch in dic:
                i = max(i, dic[ch])
            dic[ch] = j
            res = max(res, j - i)
        return res