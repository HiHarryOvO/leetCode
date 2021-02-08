class Solution {
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new LinkedList<>();
        Deque<String> path = new ArrayDeque<>();
        dfs(s, 0, len, path, res);
        return res;
    }

    public void dfs(String s, int start, int end, Deque<String> path, List<List<String>> res) {
        if (start == end) {
            res.add(new LinkedList<String>(path));
        }

        // 使用一个循环嵌套DFS和回溯
        for (int i = start; i < end; i++) {
            if (!isPal(s, start, i)) {
                continue;
            }
            path.addLast(s.substring(start, i+1));
            dfs(s, i+1, end, path, res);
            path.removeLast();
        }
    }

    // 检查是否是回文字符串，包括right
    public boolean isPal(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}


// 改进：使用动态优化改进回文字符串的判断
class Solution {
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new LinkedList<>();
        Deque<String> path = new ArrayDeque<>();

        if (len == 0) {
            return res;
        }

        // 当s[i] == s[j]时，判断dp[i+1][j-1]
        boolean[][] dp = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if ((s.charAt(right) == s.charAt(left)) && (right - left <= 2 || dp[left+1][right-1])) {
                    dp[left][right] = true;
                } else {
                    dp[left][right] = false;
                }
            }
        }

        dfs(s, 0, len, dp, path, res);
        return res;
    }

    public void dfs(String s, int start, int end, boolean[][] dp,  Deque<String> path, List<List<String>> res) {
        if (start == end) {
            res.add(new LinkedList<String>(path));
        }

        // 使用一个循环嵌套DFS和回溯
        for (int i = start; i < end; i++) {
            if (!dp[start][i]) {
                continue;
            }
            path.addLast(s.substring(start, i+1));
            dfs(s, i+1, end, dp, path, res);
            path.removeLast();
        }
    }
}