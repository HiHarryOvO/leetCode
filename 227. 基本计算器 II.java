class Solution {
    public int calculate(String s) {
        int n = s.length();
        int sign = 1;
        // 1表示乘法，-1表示除法，0表示无
        int await = 0;
        int i = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (i < n) {
            if (s.charAt(i) == '+') {
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (s.charAt(i) == '*') {
                await = 1;
                i++;
            } else if (s.charAt(i) == '/') {
                await = -1;
                i++;
            } else if (s.charAt(i) == ' ') {
                i++;
            } else {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
				
				// 如果是乘法或者除法，栈顶元素和当前数值进行运算
                if (await == 1) {
                    stack.push(stack.pop() * num);
                } else if (await == -1) {
                    stack.push(stack.pop() / num);
                } else {
                    stack.push(sign * num);
                }
                sign = 1;
                await = 0;
            }
        }
		
		// 计算栈内结果
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}