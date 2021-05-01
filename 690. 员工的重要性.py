"""
# Definition for Employee.
class Employee:
    def __init__(self, id: int, importance: int, subordinates: List[int]):
        self.id = id
        self.importance = importance
        self.subordinates = subordinates
"""

# BFS
class Solution:
    def getImportance(self, employees: List['Employee'], id: int) -> int:
        mp = {employee.id : employee for employee in employees}
        queue = collections.deque([id])
        ans = 0
        while queue:
            emp = queue.popleft()
            ans += mp[emp].importance
            for sub in mp[emp].subordinates:
                queue.append(sub)
        return ans
		
# DFS
class Solution:
    def getImportance(self, employees: List['Employee'], id: int) -> int:
        def dfs(idx: int):
            emp = mp[idx]
            total = emp.importance + sum([dfs(sub) for sub in emp.subordinates])
            return total
        
        mp = {employee.id : employee for employee in employees}

        return dfs(id)