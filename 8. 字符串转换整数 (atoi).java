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
	
	// 方法2：DFA，定义状态转移表，然后执行操作
	public int myAtoi(String s) {
        Automation auto = new Automation();
        for (char c : s.toCharArray()) {
            auto.get(c);
        }
        return (int)(auto.sign * auto.ans);
    }
}

// 定义我们的DFA（方法2）
class Automation {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    // 定义状态转移表，四列分别表示：空格，正负号，数字，其他
    private Map<String, String[]> table = new HashMap<>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    // 根据状态执行操作，start和end可以直接跳过
    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = 10 * ans + (int)c - (int)'0';
            ans = (sign == 1)? Math.min(ans, (long) Integer.MAX_VALUE): Math.min(ans, - (long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = (c == '+')? 1 : -1;
        }
    }

    public int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        
        if (c == '+' || c == '-') {
            return 1;
        }

        if (Character.isDigit(c)) {
            return 2;
        }

        return 3;
    }
}
