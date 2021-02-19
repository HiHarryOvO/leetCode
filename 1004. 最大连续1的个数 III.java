// 解法1：滑动窗口
class Solution {
    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        // 移动右端点
        for (int right = 0; right < n; right++) {
            rsum += 1 - A[right];
            // 移动左端点至满足题意的最长位置
            while (lsum < rsum - K) {
                lsum += 1 - A[left];
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

// 解法2：维护单调变长的窗口
class Solution {
    public int longestOnes(int[] A, int K) {
        int l = 0, r = 0;
        while (r < A.length) {
        	// r始终右移
        	// 引入0时，消耗K
            if (A[r++] == 0) K--;
            // K < 0时，触发l右移
            // l右移且丢弃0时，返还K
            if (K < 0 && A[l++] == 0) K++;
        }
        // 左闭右开的区间
        return r - l;
    }
}