class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num); // num入栈
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop(); // 栈顶和popped当前元素相等时弹出
                i++;
            }
        }
        return stack.isEmpty(); // 正确情况下，所有元素推入后都被弹出，此时栈为空
    }
}