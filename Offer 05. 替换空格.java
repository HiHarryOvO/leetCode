class Solution {
    public String replaceSpace(String s) {
        StringBuilder strB = new StringBuilder();
        // 遍历s
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                strB.append("%20");
            } else {
                strB.append(s.charAt(i));
            }
        }
        return strB.toString();
    }
}