# 方法1：深度优先搜索 + 三色标记
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        n = len(graph)
        # 记录状态，0 - 未访问过；1 - 正在访问 or 访问已结束且在环中；2 - 安全节点
        color = [0] * n

        def safe(x):
            # 如果已经被访问过，直接判断是否为安全节点
            if color[x] > 0:
                return color[x] == 2

            color[x] = 1
            for y in graph[x]:
                # 递归地判断从 x 起始的路径，如果路径上遇到节点类型 1，则路径上处处为 1
                # 使用返回值 False 将路径上有环的信息向上层反馈
                if not safe(y):
                    return False
            
            # 遍历 x 的路径结束 并且 路径上没有节点类型 1，则为安全节点
            color[x] = 2
            return True

        return [i for i in range(n) if safe(i)]  # 每个节点为起点都要判断一次


# 方法2：
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        n = len(graph)

        # 获得反图
        rev = [[] for _ in range(n)]
        for x, ys in enumerate(graph):
            for y in ys:
                rev[y].append(x)

        # 出度：从该顶点出发的边的数量
        # 入度：进入该顶点的边的数量
        # 获得每个点的出度（反图的入度）
        in_deg = [len(ys) for ys in graph]
        # 出度为 0 （反图的入度为 0）的点作为起点
        q = collections.deque([i for i, deg in enumerate(in_deg) if deg == 0])

        while q:
            ind = q.popleft()
            # 找出原图中所有指向 ind 的点
            for x in rev[ind]:
                # 它们的出度减一
                in_deg[x] -= 1
                # 如果出度为 0，就作为新的起点
                if in_deg[x] == 0:
                    q.append(x)

        # 找 in_deg 中为 0 的节点
        return [i for i in range(n) if in_deg[i] == 0]