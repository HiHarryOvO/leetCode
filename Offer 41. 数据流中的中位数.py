class MedianFinder:

    def __init__(self):
        """
        initialize your data structure here.
        """

        # 左边是大顶堆，右边是小顶堆
        self.left, self.right = [], []


    def addNum(self, num: int) -> None:
        if len(self.left) == len(self.right):
            heapq.heappush(self.left, -num)
            heapq.heappush(self.right, -heapq.heappop(self.left))
        else:
            heapq.heappush(self.right, num)
            heapq.heappush(self.left, -heapq.heappop(self.right))


    def findMedian(self) -> float:
        return (self.right[0] - self.left[0]) / 2 if len(self.left) == len(self.right) else self.right[0]


class MedianFinder:

    def __init__(self):
        """
        initialize your data structure here.
        """

        # 左边是大顶堆，右边是小顶堆
        self.left, self.right = [], []


    def addNum(self, num: int) -> None:
        if len(self.left) == len(self.right):
            heapq.heappush(self.right, -heapq.heappushpop(self.left, -num))
        else:
            heapq.heappush(self.left, -heapq.heappushpop(self.right, num))


    def findMedian(self) -> float:
        return (self.right[0] - self.left[0]) / 2 if len(self.left) == len(self.right) else self.right[0]


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()