class Solution {
	// 在method外定义变量，有助于使dfs参数更少
    // 储存对应情况
    private Map<Character, String> table = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
    private char[] charArr;
    private List<String> ans = new ArrayList<>();
    private StringBuilder combine = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return ans;
        }
        charArr = digits.toCharArray();
        dfs(0);
        return ans;
    }

    // 深度优先搜索
    public void dfs(int idx) {
        if (idx == charArr.length) {
            ans.add(combine.toString());
            return;
        }
        
		// 遇到非2~9的字符（这一点标答中似乎没有考虑？）
        if (!table.containsKey(charArr[idx])) {
            dfs(idx + 1);
        }

        String letters = table.get(charArr[idx]);

        for (int i = 0; i < letters.length(); i++) {
            combine.append(letters.charAt(i));
            dfs(idx + 1);
			// 回溯
            combine.deleteCharAt(combine.length() - 1);
        }
    }
}
