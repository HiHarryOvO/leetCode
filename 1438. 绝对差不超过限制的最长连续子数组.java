// 解法1：滑动窗口 + 有序集合
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int l = 0, maxLen = 0;
        // 创建有序集
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int r = 0; r < nums.length; r++) {
            // 计数类map插入的简单写法
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            // 条件不满足时左端点向右收缩，更新有序集
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            // 更新结果
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}

// 解法2：滑动窗口 + 单调队列
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int l = 0, maxLen = 0;
        Deque<Integer> maxQ = new ArrayDeque<>();
        Deque<Integer> minQ = new ArrayDeque<>();
        for (int r = 0; r < nums.length; r++) {
            // 更新队列
            while (!maxQ.isEmpty() && maxQ.peekLast() < nums[r]) {
                maxQ.pollLast();
            }
            while (!minQ.isEmpty() && minQ.peekLast() > nums[r]) {
                minQ.pollLast();
            }
            maxQ.offerLast(nums[r]);
            minQ.offerLast(nums[r]);

            // 如果大于limit则左端点右移
            // 标答中判断isEmpty()在while语句中，其实也可不判断是否为空
            while (!maxQ.isEmpty() && !minQ.isEmpty() && maxQ.peekFirst() - minQ.peekFirst() > limit) {
                if (nums[l] == maxQ.peekFirst()) {
                    maxQ.removeFirst();
                }
                if (nums[l] == minQ.peekFirst()) {
                    minQ.removeFirst();
                }
                l++;
            }
            // 更新结果
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}