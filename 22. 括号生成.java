class Solution {
    private List<String> ans = new ArrayList<>();
    private StringBuilder combine = new StringBuilder();
    private int num;

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return ans;
        }
        num = 2 * n;
        // 深度优先搜索
        dfs(0, n, n);
        return ans;
    }

    public void dfs(int idx, int left, int right) {
        if (idx == num) {
            ans.add(combine.toString());
            return;
        }

        List<Character> parens = new ArrayList<>();
        // 确定不同情况（取决于left和right值比较）下，可供选择的括号
        // 原则：在一个结果中，从左到右进行统计时，左括号的数量始终大于等于右括号数量
        if (left < right && left != 0) {
            parens.add('(');
            parens.add(')');
        } else if (left != 0){
            parens.add('(');
        } else {  // 左括号用尽的时候，只能选择右括号
             parens.add(')');
        }

        for (char p : parens) {
            combine.append(p);
            if (p == '(') {
                dfs(idx + 1, left - 1, right);
            } else {
                dfs(idx + 1, left, right - 1);
            }
            // 做回溯
            combine.deleteCharAt(combine.length() - 1);
        }
    }
}
