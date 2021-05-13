# 借0的解法（直观解法）
class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        nums.sort()

        zeros = 0
        for i, num in enumerate(nums):
            if num == 0:
                zeros += 1
            else:
                if i != 0 and nums[i - 1] != 0:
                    diff = nums[i] - nums[i - 1]
                    if diff < 1 or diff - 1 > zeros:
                        return False
                    elif diff > 1:
                        zeros -= diff - 1
        return True


# 集合
class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        repeat = set()
        ma, mi = 0, 14
        for num in nums:
            # 注意这里不要把0算进去
            if num == 0:
                continue
            ma = max(ma, num)
            mi = min(mi, num)
            if num in repeat:
                return False
            repeat.add(num)
        return ma - mi < 5


# 排序
class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        nums.sort()
        # 记录最小数（尽量不包括0）的位置
        joker = 0
        # 细节：只遍历到倒数第二个数
        for i in range(len(nums) - 1):
            if nums[i] == 0:
                joker += 1
            elif nums[i] == nums[i + 1]:
                return False
        return nums[-1] - nums[joker] < 5