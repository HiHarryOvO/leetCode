# 解法1：哈希表，集合
class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        HIGH_BIT = 30

        x = 0
        for k in range(30, -1, -1):
            # 创建集合，保留从最高位到第k位的部分
            seen = set()
            for num in nums:
                seen.add(num >> k)

            # x只包含最高位到第k+1位的部分，x_next是目标，目标的第k位 置1
            x_next = x * 2 + 1
            found = False

            for num in nums:
                if x_next ^ (num >> k) in seen:
                    found = True
                    break

            # 如果无法匹配，那么第k位只能置0了        
            if found:
                x = x_next
            else:
                x = x_next - 1

        return x

# 解法2：字典树
# 创建字典树类
class Trie:
    def __init__(self):
        # 左0右1
        self.left = None
        self.right = None

class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        root = Trie()
        HIGH_BIT = 30

        def add(num):
            cur = root
            for k in range(HIGH_BIT, -1, -1):
                bit = (num >> k) & 1
                if bit == 0:
                    cur.left = Trie() if not cur.left else cur.left
                    cur = cur.left
                else:
                    cur.right = Trie() if not cur.right else cur.right
                    cur = cur.right
        
        def check(num):
            x = 0
            cur = root
            # 由于异或运算的性质，这里进入分支的方向与add时相反
            # 例如，第k位为1，那么我们的字典树最好向左(0)查找，这样异或能够得到1
            for k in range(HIGH_BIT, -1, -1):
                bit = (num >> k) & 1
                # x提前乘以2
                x *= 2
                if bit == 0:
                    if cur.right:
                        x += 1
                        cur = cur.right
                    else:
                        cur = cur.left
                else:
                    if cur.left:
                        x += 1
                        cur = cur.left
                    else:
                        cur = cur.right
            return x
        
        ans = 0
        add(nums[0])
        for i in range(1, len(nums)):
            ans = max(ans, check(nums[i]))
            add(nums[i])
        
        return ans