class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        int sign = 1;
        int ret = 0;
        int i = 0, n = s.length();
        while (i < n) {
            if (s.charAt(i) == '+') {
                sign = stack.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = - stack.peek();
                i++;
            } else if (s.charAt(i) == '(') { // 出现左括号的时候推入当前符号
                stack.push(sign);
                i++;
            } else if (s.charAt(i) == ')') { // 出现右括号的时候弹出栈顶符号
                stack.pop();
                i++;
            } else if (s.charAt(i) == ' ') {
                i++;
            } else {
                int num = 0;
                // 先获得具体数值，然后计算结果
                // 注意：不要忘记此处的 i < n 条件，防止越界
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }
}