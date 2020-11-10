class Solution {
    // 方法1：竖式乘法
    public String multiply(String num1, String num2) {
        int n = num2.length();
        String result = "0";

        if (num1.charAt(0) == '0') {
            return result;
        }

        // 把num2的每一位和整个num1相乘，然后结果相加
        for (int i = 0; i < n; i++) {
            char cur = num2.charAt(n - 1 - i);
            if (cur == '0') {
                continue;
            }
            String curStr = multi(num1, cur, i);
            // curStr 长度一定大于等于 result
            result = addStr(curStr, result);
        }
        return result;
    }

    // 竖式乘法的相乘部分
    public String multi(String num1, char cur, int idx) {
        StringBuilder res = new StringBuilder();
        int n1 = num1.length();
        int first;
        int second = (int)cur - (int)'0';
        // 设置进位
        int carry = 0;
        for (int j = 0; j < n1; j++) {
            char ch = num1.charAt(n1 - 1- j);
            first = (int)ch - (int)'0';
            int curDigit = (first * second + carry) % 10;
            carry = (first * second + carry) / 10;
            
            res.append(curDigit);
        }
        
        if (carry != 0) {
            res.append(carry);
        }

        res = res.reverse();

        // 竖式乘法中的错位相加，本质上是在阶段计算结果后面加了几个0
        while (idx != 0) {
            res.append('0');
            idx -= 1;
        }

        return res.toString();
    }

    // 竖式乘法的加法部分
    public String addStr(String curStr, String result) {
        int n1 = curStr.length();
        int n2 = result.length();

        StringBuilder res = new StringBuilder();
        int carry = 0;
        int first, second;

        for (int i = 0; i < n1; i++) {
            first = (int)curStr.charAt(n1 - 1 - i) - (int)'0';
            second = (i < n2)? (int)result.charAt(n2 - 1 - i) - (int)'0': 0;
            int curDigit = (first + second + carry) % 10;
            carry = (first + second + carry) / 10;
            res.append(curDigit);
        }

        if (carry != 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }

    // 方法2：维护数组
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        // 结果最多为m + n位，最少为m + n - 1位
        int[] result = new int[m + n];
        int first, second;

        for (int i = m - 1; i >=0; i--) {
            first = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                second = num2.charAt(j) - '0';
                result[i+j+1] += first * second;
            }
        }

        // 从后往前维护进位
        for (int i = m + n - 1; i > 0; i--) {
            result[i-1] += result[i] / 10;
            result[i] %= 10;
        }

        // 在这里需要判断结果到底是m + n位还是m + n - 1位
        int idx = (result[0] == 0)? 1 : 0;
        StringBuilder ans = new StringBuilder();

        // 整数数组转字符串
        while (idx < m + n) {
            ans.append(result[idx]);
            idx++;
        }
        return ans.toString();
    }
}
