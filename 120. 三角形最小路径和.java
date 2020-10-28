class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 只需O(n)空间
        int[] minTotal = new int[n];

        minTotal[0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            // 从右往左更新就可以避免覆盖掉所需数值的问题
            // 每一个点的路径来自于上一行下标相同的点或上一行下标-1的点
            // 因此，从右往左更新时，覆盖掉的点不会用于求下一个点的值
            for (int j = i; j >= 0; j--) {
                int curr = triangle.get(i).get(j);
                if (j == i) {
                    minTotal[j] = minTotal[j-1] + curr;
                } else if (j == 0) {
                    minTotal[j] = minTotal[j] + curr;
                } else {
                    minTotal[j] = Math.min(minTotal[j], minTotal[j-1]) + curr;
                }
            }
        }

        // 找最小
        int minimum = minTotal[0];

        for (int i = 1; i < n; i++) {
            minimum = (minTotal[i] < minimum)? minTotal[i]: minimum;
        }

        return minimum;
    }
}
