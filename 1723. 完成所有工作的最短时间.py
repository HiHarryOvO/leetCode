# DP + 状态压缩（超时啦，扑街）
class Solution:
    def minimumTimeRequired(self, jobs: List[int], k: int) -> int:
        # python版的Integer.numberOfTrailingZeros()
        def ctz(v):
            return (v & -v).bit_length() - 1
        
        n = len(jobs)
        dp = [[0] * (1 << n) for _ in range(k)]
        for i in range(1, 1 << n):
            x, y = ctz(i), i - (1 << ctz(i))
            dp[0][i] = dp[0][y] + jobs[x]

        for i in range(1, k):
            for j in range(0, 1 << n):
                minn, x = 1e9, j
                while x != 0:
                    minn = min(minn, max(dp[i-1][j-x], dp[0][x]))
                    x = (x - 1) & j
                dp[i][j] = minn
        
        return dp[k-1][(1 << n) - 1]


# 二分查找 + 回溯 + 剪枝
class Solution:
    def minimumTimeRequired(self, jobs: List[int], k: int) -> int:
        def backtrack(workloads, limit, i):
            if i >= len(jobs):
                return True

            cur = jobs[i]
            for j in range(0, k):
                if workloads[j] + cur <= limit:
                    workloads[j] += cur
                    if backtrack(workloads, limit, i + 1):
                        return True
                    workloads[j] -= cur

                # 如果j没有被分配工作，那么j+1及之后都不用被分配了
                # 如果j分配jobs[i]等于limit但还是失败了，那么一定无法分配成功
                if workloads[j] == 0 or workloads[j] + cur == limit:
                    break

            return False

        # 从更大的任务开始分配
        jobs.sort(reverse=True)
        # 注意二分查找的边界设置
        low, high = max(jobs), sum(jobs)
        # 二分查找，满足条件时向左查找，否则向右
        while low < high:
            mid = (low + high) // 2
            workloads = [0] * k
            if backtrack(workloads, mid, 0):
                high = mid
            else:
                low = mid + 1
        return low 