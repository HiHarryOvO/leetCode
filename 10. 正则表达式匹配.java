class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 使用动态规划
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;

        // 动态规划方程，注意i是从0开始的（空字符串也能匹配类似 "a*b*c*" 的形式）
        for (int i = 0; i <= m; i++) {
            // 按照我的理解，j为0的地方默认为false
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) != '*') {
                    // 这里不能写成 _match(s, p, i, j) && dp[i-1][j-1]，会产生数组越界问题
                    if (_match(s, p, i, j)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    // j等于1时一定不会匹配到这里，因为题目说明了 *前面都能匹配到有效字符
                    if (_match(s, p, i, j-1)) {
                        dp[i][j] = (dp[i-1][j] || dp[i][j-2]);
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }

    // 匹配字符的方法
    public boolean _match(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }

        if (p.charAt(j-1) == '.') {
            return true;
        }

        return s.charAt(i-1) == p.charAt(j-1);
    }
}
