// 解法1：递归分治
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    boolean recur(int[] postorder, int i, int j) {
        // 该子树有1个或0个节点时，一定成立
        if (i >= j) {
            return true;
        }

        // 从最左边开始计算
        int p = i;
        while (postorder[p] < postorder[j]) {
            p++;
        }
        // 记录右子树出现的位置，作为递归划分的位置
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        // 看指针是否走到末尾，并且递归判断左子树和右子树
        return (p == j) && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }
}

// 解法2：辅助单调栈
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> stack = new LinkedList<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) {
                return false;
            }

            while (!stack.isEmpty() && stack.peek() > postorder[i]) {
                root = stack.pop();
            }

            stack.push(postorder[i]);
        }

        return true;
    }
}