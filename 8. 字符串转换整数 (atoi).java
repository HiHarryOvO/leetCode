class Solution {
    // 方法1：if-else多重嵌套
    public int myAtoi(String s) {
        int sign = 1;
        long result = 0;
        // 用以寻找第一个非空字符
        boolean checkFirst = true;

        for (char c : s.toCharArray()) {
            int curNum = (int)c - (int)'0';
            if (checkFirst && c == ' ') {
                continue;
            } else if (checkFirst && (c == '+' || c == '-')) {
                sign = (c == '-')? -1: 1;
                checkFirst = false;
            } else if (checkFirst && (curNum >= 0 && curNum <= 9)) {
                result = 10 * result + sign * curNum;
                checkFirst = false;
            } else if (checkFirst) {
                return 0;
            } else if (!checkFirst  && (curNum >= 0 && curNum <= 9)) {
                result = 10 * result + sign * curNum;
                if (result >= (long)Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (result <= (long)Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else if (!checkFirst) {
                break;
            }
        }
        return (int)result;
    }
}
