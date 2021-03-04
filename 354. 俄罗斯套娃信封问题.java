// 动态规划
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        // 对w列按升序排列，w列相同时对h列按降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });
        // 动态规划搜索h列
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < n ; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 动态规划方程
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

// 贪心 + 二分查找
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        // 对w列按升序排列，w列相同时对h列按降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });
        // 贪心 + 二分查找
        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; i++) {
            int num  = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int num) {
        int low = 0, high = f.size();
        // 还是注意二分查找写法，这里区间是 [low, high)
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}