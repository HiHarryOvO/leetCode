class Solution {
    public String longestPalindrome(String s) {
        String longStr = "";

        for (int i = 0; i < s.length(); i++) {
            // 判断回文子串，从边界情况开始，向外扩充
            // 有两种边界情况，一是aba类型（或者说是a型），二是abba类型（或bb型）
            
            // 边界情况1
            String curr = "" + s.charAt(i);
            int j = 1;
            while (i - j >=0 && i + j < s.length()) {
                if (s.charAt(i - j) == s.charAt(i + j)) {
                    curr = s.charAt(i - j) + curr + s.charAt(i + j);
                } else {
                    break;
                }
                j++;
            }

            if (curr.length() >= longStr.length()) {
                longStr = curr;
            }

            // 边界情况2
            curr = "";
            if (i != s.length() - 1) {
                j = 0;
                while (i - j >= 0 && i + 1 + j < s.length()) {
                    if (s.charAt(i - j) == s.charAt(i + 1 + j)) {
                        curr = s.charAt(i - j) + curr + s.charAt(i + 1 + j);
                    } else {
                        break;
                    }
                    j++;
                }
            }

            if (curr.length() >= longStr.length()) {
                longStr = curr;
            }
        }

        return longStr;
    }
}
