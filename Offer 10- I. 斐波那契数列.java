class Solution {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int last2 = 0, last1 = 1, ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (last2 + last1) % 1000000007;
            last2 = last1;
            last1 = ans;
        }
        return ans;
    }
}