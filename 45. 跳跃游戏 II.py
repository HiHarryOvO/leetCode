class Solution:
    def jump(self, nums: List[int]) -> int:
        n = len(nums)
        # step 记录步数
        # end 指示当前步的范围
        # max_pos 指示在当前步下，可以到达的最远范围
        step, end, max_pos = 0, 0, 0
        for i in range(n - 1):
            max_pos = max(max_pos, i + nums[i])
            if i == end:
                end = max_pos
                step += 1
        return step