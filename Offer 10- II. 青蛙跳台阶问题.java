class Solution {
    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }

        int last2 = 1, last1 = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (last2 + last1) % 1000000007;
            last2 = last1;
            last1 = sum;
        }
        return sum;
    }
}