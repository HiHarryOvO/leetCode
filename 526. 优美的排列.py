# 方法1：回溯
class Solution:
    def countArrangement(self, n: int) -> int:
        match = defaultdict(list)
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if i % j == 0 or j % i == 0:
                    match[i].append(j)

        ans = 0
        vis = set()

        def dfs(k):
            if k == n + 1:
                nonlocal ans
                ans += 1
                return
            
            for idx in match[k]:
                if idx in vis:
                    continue
                vis.add(idx)
                dfs(k + 1)
                vis.discard(idx)

        dfs(1)
        return ans


# 方法2：状态压缩 + DP
class Solution:
    def countArrangement(self, n: int) -> int:
        # f 表示当前状态下的完美排列的数量
        f = [0] * (1 << n)
        f[0] = 1

        # 以 100110 为例，就是选择 2,3,6 作为优美排列的前三项。
        # 当我们计算 f[100110] 的时候，我们考虑 2,3,6 这三个数哪个能放在排列的第 3 位
        # 3 可以，因此把 3 放在第 3 位的优美排列数量等于把 2,6 放在前两位的优美排列数量
        # 因此 f[100110] += f[100010]
        # 同理 6 也可以，因此把 6 放在第 3 位的优美排列数量等于把 2,3 放在前两位的优美排列数量
        # 因此 f[100110] += f[000110]
        for mask in range(1, 1 << n):
            # num 是当前考虑的位数，即原问题中 第 i 位 中的 i
            num = bin(mask).count("1")
            for i in range(n):
                # 首先数字 (i + 1) 必须在当前的状态中，其次必须可以整除 num 或被 num 整除
                if mask & (1 << i) and (num % (i + 1) == 0 or (i + 1) % num == 0):
                    f[mask] += f[mask ^ (1 << i)]
        return f[(1 << n) - 1]
