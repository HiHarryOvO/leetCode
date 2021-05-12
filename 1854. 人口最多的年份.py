class Solution:
    def maximumPopulation(self, logs: List[List[int]]) -> int:
        delta = [0] * 101
        start = 1950

        # 构建差分数组
        for birth, death in logs:
            delta[birth - start] += 1
            delta[death - start] -= 1
        
        max_ppl = 0
        earliest_year = 0
        curr_ppl = 0

        for i in range(len(delta)):
            curr_ppl += delta[i]
            if curr_ppl > max_ppl:
                max_ppl = curr_ppl
                earliest_year = start + i
        return earliest_year