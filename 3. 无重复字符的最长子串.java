class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // 创建一个字符集
        Set<Character> occ = new HashSet<>();
        // 设置右指针
        int rk = -1, ans = 0;

        for (int i = 0; i < n; i++) {
            if (i != 0) {
                // 左指针的右移
                occ.remove(s.charAt(i - 1));
            }

            // 不断向右移动右指针，遇到重复情况时停止
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1));
                rk++;
            }

            // 比较当前值和已记录的最长值
            ans = Math.max(ans, rk + 1 - i);
        }
        return ans;
    }
}
