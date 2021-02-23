class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        // 先计算不使用技能时的满意顾客数
        for (int i = 0; i < customers.length; i++) {
            res += customers[i] * (grumpy[i] ^ 1);
        }

        int l = 0, currSum = res;
        // 滑动窗口法，求最多满意数
        for (int r = 0; r < customers.length; r++) {
            if (r < X && grumpy[r] == 1) {
                currSum += customers[r];
            } else if (r >= X){ // r >= X的时候，窗口平移
                currSum += customers[r] * grumpy[r] - customers[l] * grumpy[l];
                l++;
            }
            res = Math.max(res, currSum);
        }
        return res;
    }
}