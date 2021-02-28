class Solution {
    public boolean isMonotonic(int[] A) {
        int n = A.length;
        boolean inc = true, dec = true;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] > A[i + 1]) {
                inc = false;
            }
            if (A[i] < A[i + 1]) {
                dec = false;
            }
        }
        // 当递增和递减同时出现时，inc和dec都是false，返回false
        return inc || dec;
    }
}