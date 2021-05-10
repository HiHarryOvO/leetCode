import collections

class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        if not nums or k == 0:
            return []
        deque = collections.deque()
        # 形成窗口前（无需删除左端元素）
        for i in range(k):
            # 维护单调队列的递减性
            while deque and deque[-1] < nums[i]:
                deque.pop()
            deque.append(nums[i])

        ans = [deque[0]]
        # 形成窗口后
        for i in range(k, len(nums)):
            # 如果要删除的元素就是当前最大值，则修改单调队列；否则该元素已经被删除
            if nums[i - k] == deque[0]:
                deque.popleft()
            while deque and deque[-1] < nums[i]:
                deque.pop()
            deque.append(nums[i])
            ans.append(deque[0])
        return ans