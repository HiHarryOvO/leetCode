class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 使用HashSet方便进行判断，contains()只需O(1)时间
        Set<String> wordset = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n+1];

        if (n == 0) {
            return false;
        }
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 动态规划
                if (dp[j] && wordset.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                };
            }
        }
        return dp[n];
    }
}
