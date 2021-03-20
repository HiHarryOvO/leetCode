class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String s : tokens) {
            // 如果s是运算符
            if (s.length() == 1 && (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/')) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                int res = calculate(num1, num2, s.charAt(0));
                stack.push(res);
            } else {
                // 否则s为操作数
                stack.push(toNum(s));
            }
        }
        return stack.pop();
    }

    // 将字符串转换为数字
    int toNum(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 注意加一个条件，可能有负号
            if(Character.isDigit(s.charAt(i))) {
                res *= 10;
                res += s.charAt(i) - '0';
            }
        }
        return (s.charAt(0) == '-')? - res : res;
    }

    // 执行计算
    int calculate(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }
}