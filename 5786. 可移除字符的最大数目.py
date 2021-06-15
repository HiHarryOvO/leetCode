class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:
        ns, np = len(s), len(p)
        n = len(removable)

        def check(k: int) -> bool:
            state = [True] * ns
            for i in range(k):
                state[removable[i]] = False

            j = 0
            for i in range(ns):
                if state[i] and s[i] == p[j]:
                    j += 1
                if j == np:
                    return True
            return False

        low, high = 0, n + 1
        while low < high:
            mid = (low + high) // 2
            if check(mid):
                low = mid + 1
            else:
                high = mid
        return low - 1