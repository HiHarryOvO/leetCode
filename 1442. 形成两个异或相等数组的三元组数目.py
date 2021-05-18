# 二重循环
class Solution:
    def countTriplets(self, arr: List[int]) -> int:
        n = len(arr)
        # 求前缀异或
        pre = [0] * (n + 1)
        for i in range(n):
            pre[i+1] = pre[i] ^ arr[i]

        left, right = 0, 1
        ans = 0
        while right < n:
            left = 0
            while left < right:
                curr = pre[right+1] ^ pre[left]
                if curr == 0:
                    ans += right - left
                left += 1
            right += 1

        return ans

# 一重循环
import collections

class Solution:
    def countTriplets(self, arr: List[int]) -> int:
        n = len(arr)
        # 求前缀异或
        pre = [0] * (n + 1)
        for i in range(n):
            pre[i+1] = pre[i] ^ arr[i]

        ans = 0
        count, total = collections.Counter(), collections.Counter()

        for k in range(n):
            if pre[k+1] in count:
                ans += count[pre[k+1]] * k - total[pre[k+1]]
            count[pre[k]] += 1
            total[pre[k]] += k

        return ans