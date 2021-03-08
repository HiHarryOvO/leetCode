class Solution {
    public int minCut(String s) {
        int len = s.length();
        // 动态规划找出所有回文串，O(n^2)
        boolean[][] dp = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                // 动态规划方程
                dp[left][right] = (s.charAt(left) == s.charAt(right)) && (right - left <= 2 || dp[left + 1][right - 1]);
            }
        }

        // 动态规划寻找最小分割次数，O(n^2)
        int[] f = new int[len];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < len; i++) {
            if (dp[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i]) {
                        // 动态规划方程
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[len - 1];
    }
}