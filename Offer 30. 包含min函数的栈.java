class MinStack {
    // 数据栈
    Deque<Integer> stackA;
    // 辅助栈，单调栈
    Deque<Integer> stackB;

    /** initialize your data structure here. */
    public MinStack() {
        stackA = new LinkedList<Integer>();
        stackB = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        stackA.push(x);
        if (stackB.isEmpty() || x <= stackB.peek()) {
            stackB.push(x);
        }
    }
    
    public void pop() {
        int x = stackA.pop();
        if (stackB.peek() == x) {
            stackB.pop();
        }
    }
    
    public int top() {
        return stackA.peek();
    }
    
    public int min() {
        return stackB.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */