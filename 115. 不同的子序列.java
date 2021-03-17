// DFS，超时，GG
class Solution {
    int sols = 0;

    public int numDistinct(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return 0;
        }

        dfs(s, t, 0, 0);
        return sols;
    }

    void dfs(String s, String t, int start, int pos) {
        if (pos == t.length()) {
            sols++;
            return;
        }
        
        for (int i = start; i <= s.length() - t.length() + pos; i++) {
            if (s.charAt(i) == t.charAt(pos)) {
                dfs(s, t, i + 1, pos + 1);
            }
        }

        return;
    }
}

// 动态规划
class Solution {
    public int numDistinct(String s, String t) {
        int sLen = s.length(), tLen = t.length();

        // s长度不可以小于t
        if (sLen < tLen) {
            return 0;
        }

        // dp[i][j]表示在s[i:]中t[j:]出现的次数
        int[][] dp = new int[sLen + 1][tLen + 1];
        dp[sLen][tLen] = 1;
        
        for (int j = tLen; j >= 0; j--) {
            // i在sLen处时，默认为0
            for (int i = sLen - 1; i >= 0; i--) {
                // t[j:]长度为0时，一定有1个匹配
                if (j == tLen) {
                    dp[i][j] = 1;
                } else {
                    if (s.charAt(i) == t.charAt(j)) {
                        //分两种情况，s和t在i处匹配，s和t不在i处匹配
                        dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                    } else {
                        // 没有匹配到
                        dp[i][j] = dp[i + 1][j];
                    }
                }
            }
        }

        return dp[0][0];
    }
}

// 动态规划的优化（做法解释见笔记）
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n && j <= i; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j] += dp[j + 1];
                }
            }
        }

        return dp[0];
    }
}