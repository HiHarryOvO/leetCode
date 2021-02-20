// 解法1：哈希表 + 遍历
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                // 依次存储 频率，初次出现位置，最后一次出现位置
                Integer[] info = {1, i, i};
                map.put(nums[i], info);
            } else {
                map.get(nums[i])[0] += 1;
                map.get(nums[i])[2] = i;
            }
        }

        int maxCnt = 0, ans = 0;

        for (Integer[] v : map.values()) {
            if (v[0] > maxCnt) {
                ans = v[2] - v[1] + 1;
                maxCnt = v[0];
            } else if (v[0] == maxCnt) {
                ans = Math.min(ans, v[2] - v[1] + 1);
            }
        }

        return ans;
    }
}

// 解法2：滑动窗口
class Solution {
    public int findShortestSubArray(int[] nums) {
        int maxCnt = 0, ans = 50000, l = 0;
        int[] cnt1 = new int[50000];
        int[] cnt2 = new int[50000];

        // 第一次循环找到最大的频数
        for (int num : nums) {
            cnt1[num]++;
            maxCnt = Math.max(maxCnt, cnt1[num]);
        }

        // 滑动窗口
        for (int r = 0; r < nums.length; r++) {
            // 右端点扩充
            cnt2[nums[r]]++;
            // 在保持当前子数组内最大频数等于maxCnt时，不断收缩左端点
            while (cnt2[nums[r]] == maxCnt) {
                ans = Math.min(ans, r - l + 1);
                cnt2[nums[l++]]--;
            }
        }
        return ans;
    }
}