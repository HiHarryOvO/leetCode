/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
// 解法1：直接使用队列or列表存储元素
public class NestedIterator implements Iterator<Integer> {
    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        iterList(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void iterList(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                queue.offer(ni.getInteger());
            } else {
                iterList(ni.getList());
            }
        }
    }
}

// 解法2：模拟栈运行
public class NestedIterator implements Iterator<Integer> {
    private Deque<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        // 创建一个存储迭代器的栈
        stack = new LinkedList<Iterator<NestedInteger>>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // 由于题目保证调用next()前会调用hasNext()，因此可以直接访问栈顶
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> top = stack.peek();
            // 如果栈顶迭代器内遍历结束，弹出栈顶迭代器
            if (!top.hasNext()) {
                stack.pop();
                continue;
            }

            NestedInteger elem = top.next();
            if (elem.isInteger()) {
                // 如果是整数，则创建一个新的迭代器存储（注意这里需要存储的是NestedInteger而不是Integer）
                List<NestedInteger> ls = new LinkedList<>();
                ls.add(elem);
                stack.push(ls.iterator());
                return true;
            } else {
                // 不是整数则将新的嵌套列表的迭代器推入栈中
                stack.push(elem.getList().iterator());
            }
        }
        return false;
    }
}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */