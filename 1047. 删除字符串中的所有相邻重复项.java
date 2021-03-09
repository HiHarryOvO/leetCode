class Solution {
    public String removeDuplicates(String S) {
        StringBuilder stack = new StringBuilder();
        // 使用一个top变量追踪栈顶
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            // 相同时移除栈顶
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                top--;
            // 不同时推入栈中
            } else {
                stack.append(ch);
                top++;
            }
        }
        return stack.toString();
    }
}