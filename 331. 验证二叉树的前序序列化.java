// 解法1：栈
class Solution {
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        // 创建一个栈存储各个节点的剩余槽位
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        int i = 0;
        while (i < n) {
            // 循环还没结束时槽位已用完，证明一定不合法
            if (stack.isEmpty()) {
                return false;
            }

            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                // 槽位减一
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                // 获取整个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                // 槽位减1
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                // 新的2个槽位
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }
}

// 解法2：计数
class Solution {
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int slots = 1;
        int i = 0;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slots--;
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }
}