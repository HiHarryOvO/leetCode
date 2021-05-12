class Solution:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        max_dist = 0
        i = j = 0
        while i < len(nums1) and j < len(nums2):
            if i > j:
                j += 1
            elif nums1[i] > nums2[j]:
                i += 1
            else:
                max_dist = j - i if j - i > max_dist else max_dist
                j += 1
        return max_dist