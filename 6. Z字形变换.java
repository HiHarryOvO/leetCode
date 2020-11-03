class Solution {
    public String convert(String s, int numRows) {
        // 边界情况
        if (numRows == 1) {
            return s;
        }
        // 使用一个List把每一行的情况装进去
        List<StringBuilder> rows = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        boolean goDown = false;  // 使用一个布尔变量判断向上走还是向下走
        int curRow = 0;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 判断拐角
            if (curRow == 0 || curRow == numRows - 1) {
                goDown = !goDown;
            }
            curRow += (goDown)? 1 : -1;
        }

        // 合并结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}
