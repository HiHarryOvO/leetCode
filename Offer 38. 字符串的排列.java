// 无剪枝做法
class Solution {
    public String[] permutation(String s) {
        int n = s.length();
        // 使用Set防止重复
        Set<String> set = new HashSet<>();
        char[] arr = s.toCharArray();
        // 记录字符访问情况
        boolean[] visited = new boolean[n];
        StringBuilder sb = new StringBuilder();

        dfs(arr, set, sb, visited);
        return set.toArray(new String[0]);
    }

    void dfs(char[] arr, Set<String> set, StringBuilder sb, boolean[] visited) {
        if (sb.length() == arr.length) {
            set.add(sb.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(arr[i]);

                dfs(arr, set, sb, visited);

                // 回溯
                visited[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}

// 有剪枝的做法
class Solution {
    char[] arr;
    List<String> ls;

    public String[] permutation(String s) {
        arr = s.toCharArray();
        ls = new LinkedList<>();
        dfs(0);
        return ls.toArray(new String[0]);
    }

    // 搜索回溯，x表示固定第x位
    void dfs(int x) {
        // 前n-1位已经确定后，最后一位也就同时确定了
        if (x == arr.length - 1) {
            ls.add(String.valueOf(arr));
            return;
        }

        // 使用HashSet记录前x-1位确定时，第x位已使用的情况
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < arr.length; i++) {
            // 前x-1位确定时，第x位不可重复使用同一字符
            if (set.contains(arr[i])) {
                continue;
            }
            // 这里不需要对set回溯，因为返回上层递归时，本层的set会被回收
            set.add(arr[i]);
            // 交换下标为i和为x的字符
            swap(i, x);
            // 下层递归
            dfs(x + 1);
            // 回溯
            swap(i, x);
        }
    }

    void swap(int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}