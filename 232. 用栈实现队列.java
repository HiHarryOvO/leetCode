class MyQueue {
    // 栈改变原顺序，因此两个栈倒一下就是原顺序了
    // 一个负责进，一个负责出
    Deque<Integer> inputStack;
    Deque<Integer> outputStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inputStack = new ArrayDeque<>();
        outputStack = new ArrayDeque<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        inputStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 当输出栈为空时，导入所有输入栈内的元素
        if (outputStack.isEmpty()) {
            inputToOutput();
        }
        return outputStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        // 当输出栈为空时，导入所有输入栈内的元素
        if (outputStack.isEmpty()) {
            inputToOutput();
        }
        return outputStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    public void inputToOutput() {
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */