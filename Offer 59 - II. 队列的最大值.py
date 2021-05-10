import collections

class MaxQueue:

    def __init__(self):
        self.deque = collections.deque()
        self.queue = []

    def max_value(self) -> int:
        return self.deque[0] if self.deque else -1

    def push_back(self, value: int) -> None:
        self.queue.append(value)
        # 删除小于value的元素
        while self.deque and self.deque[-1] < value:
            self.deque.pop()
        self.deque.append(value)

    def pop_front(self) -> int:
        if not self.queue:
            return -1
        # 更新单调队列
        if self.queue[0] == self.deque[0]:
            self.deque.popleft()
        return self.queue.pop(0)


# Your MaxQueue object will be instantiated and called as such:
# obj = MaxQueue()
# param_1 = obj.max_value()
# obj.push_back(value)
# param_3 = obj.pop_front()