// 动态规划
class Solution {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < len; i++) {
            int p = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            dp[i + 1] = (p >= 10 && p <= 25)? dp[i] + dp[i - 1] : dp[i];
        }
        return dp[len];
    }
}

// 动态规划，优化空间
class Solution {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        // prev上上个，last上一个
        int prev = 1, last = 1;
        int curr = 1;
        for (int i = 1; i < len; i++) {
            int p = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            curr = (p >= 10 && p <= 25)? last + prev : last;

            prev = last;
            last = curr;
        }
        return last;
    }
}