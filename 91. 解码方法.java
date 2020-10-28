class Solution {
    public int numDecodings1(String s) {
        
        if (s.charAt(0) == '0') {
            return 0;
        }
        
        if (s.length() == 1) {
            return 1;
        }

        int prevSum = 1, lastSum = 1, currSum = 0;

        for (int i = 1; i < s.length(); i++) {
            int lastInt = (int)(s.charAt(i-1)) - (int)'0';
            int currInt = (int)(s.charAt(i)) - (int)'0';

            // 讨论各种边界情况（似乎做复杂了一些）
            if (lastInt == 0 && currInt == 0) {
                return 0;
            } else if (lastInt == 0) {
                currSum = lastSum;
            } else if (currInt == 0) {
                if (lastInt < 3) {
                    currSum = prevSum;
                } else {
                    return 0;
                }
            } else {
                if (10 * lastInt + currInt <= 26) {
                    currSum = prevSum + lastSum;
                } else {
                    currSum = lastSum;
                }
            }

            // 更新记录值
            prevSum = lastSum;
            lastSum = currSum;
        }

        return currSum;
    }

    public int numDecodings2(String s) {
        int n = s.length();
        // 将字符串转换为数组，处理起来更直接
        char[] ch = s.toCharArray();
        // 数组多留一个位置，方便计算第二个字符处的结果
        int[] dp = new int[n+1];

        if (ch[0] == '0') {
            return 0;
        }
        
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            if (ch[i-1] == '0' && ch[i-2] == '0') {
                return 0;
            }
            
            if (ch[i-1] != '0') {
                dp[i] = dp[i-1];
            }
            
            int num = 10 * ((int)(ch[i-2]) - (int)'0') + (int)ch[i-1] - (int)'0';
            if (num >= 10 && num <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
