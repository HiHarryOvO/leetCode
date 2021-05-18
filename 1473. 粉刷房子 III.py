class Solution:
    def minCost(self, houses: List[int], cost: List[List[int]], m: int, n: int, target: int) -> int:
        # 调整颜色为从0开始，未涂色为-1
        houses = [c - 1 for c in houses]

        dp = [[[float("inf")] * target for _ in range(n)] for i in range(m)]

        # dp[i][j][k]表示第i个房子涂成颜色j且属于第k个街区，所需的最少花费
        for i in range(m):
            for j in range(n):
                # 如果已经被涂过色了，就不用讨论这个房子的其他颜色了
                if houses[i] != -1 and houses[i] != j:
                    continue
                
                for k in range(target):
                    for j0 in range(n):
                        if j == j0:
                            if i == 0:
                                # 对于第1个房子，只能属于第1个街区
                                if k == 0:
                                    dp[i][j][k] = 0
                            else:
                                # 当前房子刷成和上一个房子一样的颜色
                                dp[i][j][k] = min(dp[i][j][k], dp[i-1][j][k])
                        elif i > 0 and k > 0:
                            # 当前房子和上一个房子颜色不一样（因此街区一定不一样）（注意防止越界条件）
                            dp[i][j][k] = min(dp[i][j][k], dp[i-1][j0][k-1])

                    # 如果 当前位置可行 并且 之前未被上色，则添加花费
                    if dp[i][j][k] != float("inf") and houses[i] == -1:
                        dp[i][j][k] += cost[i][j]
        
        # 最后的最小花费从最后一个房子取不同颜色的最小值中获得
        ans = min([dp[m-1][j][target-1] for j in range(n)])
        return ans if ans != float("inf") else -1