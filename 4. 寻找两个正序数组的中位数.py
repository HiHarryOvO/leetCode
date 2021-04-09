# 解法1：二分查找，log(m + n)复杂度
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        def getKthElement(k: int) -> int:
            index1, index2 = 0, 0
            while True:
                # 特殊情况
                if index1 == m:
                    return nums2[index2 + k - 1]
                if index2 == n:
                    return nums1[index1 + k - 1]
                if k == 1: # 结束时候的判断
                    return min(nums1[index1], nums2[index2])
                
                # 新的位置
                newIndex1 = min(index1 + k // 2 - 1, m - 1)
                newIndex2 = min(index2 + k // 2 - 1, n - 1)

                pivot1 = nums1[newIndex1]
                pivot2 = nums2[newIndex2]

                # 可以排除 (newIndex - index + 1) 个数（包括新位置所在的点）
                if pivot1 <= pivot2:
                    k -= newIndex1 - index1 + 1
                    index1 = newIndex1 + 1
                else:
                    k -= newIndex2 - index2 + 1
                    index2 = newIndex2 + 1
        
        m, n = len(nums1), len(nums2)
        totalLen = m + n
        if totalLen % 2 == 1:
            # 奇数个元素
            return getKthElement((totalLen + 1) // 2)
        else:
            # 偶数个元素
            return (getKthElement(totalLen // 2) + getKthElement(totalLen // 2 + 1)) / 2