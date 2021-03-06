class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < 2 * n - 1; i++) {
            // 维护单调栈
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }
}