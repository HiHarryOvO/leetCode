class TimeMap:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.mp = {}


    def set(self, key: str, value: str, timestamp: int) -> None:
        if key in self.mp.keys():
            self.mp[key].append((value, timestamp))
        else:
            self.mp[key] = [(value, timestamp)]


    def get(self, key: str, timestamp: int) -> str:
        if key not in self.mp.keys():
            return ""

        n = len(self.mp[key])
        arr = self.mp[key]
        l, r = 0, n - 1
        while l <= r:
            mid = (l + r) // 2
            if arr[mid][1] > timestamp:
                r = mid - 1
            else:
                l = mid + 1
        return arr[r][0] if r != -1 else ""


# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)