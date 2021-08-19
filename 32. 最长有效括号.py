# 方法1：DP
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        if not s:
            return 0

        n = len(s)
        # dp[i] 表示以 s[i] 结尾的最长有效括号的长度 
        dp = [0] * n
        for i in range(1, n):
            if s[i] == "(":
                continue
            
            # 注意防止越界
            if s[i - 1] == "(":
                dp[i] = dp[i - 2] + 2 if i >= 2 else 2
            elif i - dp[i - 1] > 0 and s[i - dp[i - 1] - 1] == "(":
                dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2 if i - dp[i - 1] > 1 else dp[i - 1] + 2
        return max(dp)

# 方法2：栈
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        if not s:
            return 0

        stack = [-1]
        ans = 0
        for i, ch in enumerate(s):
            if ch == "(":
                stack.append(i)
            else:
                stack.pop()
                if stack:
                    ans = max(ans, i - stack[-1])
                else:
                    stack.append(i)
        return ans


# 方法3：贪心
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        ans = 0

        left = right = 0
        for ch in s:
            if ch == "(":
                left += 1
            else:
                right += 1
            
            if left == right:
                ans = max(ans, 2 * right)
            elif left < right:
                left = right = 0

        left = right = 0
        for ch in s[::-1]:
            if ch == "(":
                left += 1
            else:
                right += 1
            
            if left == right:
                ans = max(ans, 2 * right)
            elif left > right:
                left = right = 0

        return ans