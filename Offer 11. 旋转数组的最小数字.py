class Solution:
    def minArray(self, numbers: List[int]) -> int:
        left, right = 0, len(numbers) - 1
        while left <= right:
            mid = (left + right) // 2
            if numbers[mid] < numbers[right]:
                # 这种情况mid可能是最终结果，不能去掉
                right = mid
            elif numbers[mid] > numbers[right]:
                # 这种情况mid一定不是最终结果，因此可以去掉
                left = mid + 1
            else:
                # 缩小范围
                right -= 1
        return numbers[left]