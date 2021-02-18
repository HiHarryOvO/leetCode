// 暴力贪心
class Solution {
    public int minKBitFlips(int[] A, int K) {
        int times = 0, len = A.length;
        // 贪心算法，滑动窗口
        for (int i = 0; i <= len - K; i++) {
            if (A[i] == 0) {
                flip(A, K, i);
                times++;
            } else {
                continue;
            }
        }
        if (containsZero(A, len - K)) {
            return -1;
        } else {
            return times;
        }
    }

    // 翻转
    public void flip(int[] A, int K, int start) {
        for (int i = start; i < start + K; i++) {
            A[i] ^= 1;
        }
    }

    // 判断最后数组最后是否还有0
    public boolean containsZero(int[] A, int start) {
        for (int i = start; i < A.length; i++) {
            if (A[i] == 0) {
                return true;
            }
        }
        return false;
    }
}

// 差分数组 + 贪心
class Solution {
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; i++) {
            revCnt ^= diff[i];
            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ans++;
                diff[i + K] ^= 1;
                revCnt ^= 1;
            }
        }
        return ans;
    }
}

// 滑动窗口 + 贪心
class Solution {
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; i++) {
            if (i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                A[i - K] -= 2;
            }

            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ans++;
                revCnt ^= 1;
                A[i] += 2;
            }
        }
        return ans;
    }
}