// 滑动窗口
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        // 左右指针
        int left = 0, right = 0;
        int len = s.length();
        // 记录最大值
        int maxLen = 0;
        while (right < len) {
            char r = s.charAt(right);
            map.put(r, map.getOrDefault(r, 0) + 1);
            while (map.get(r) > 1) {
                char l = s.charAt(left);
                map.put(l, map.get(l) - 1);
                left++;
            }
            // 比较记录的最大值和当前窗口长度
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}

// 优化滑动窗口
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // map存储某个字符上一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        // 左右指针
        int left = 0, right = 0;
        int len = s.length();
        // 记录最大值
        int maxLen = 0;
        while (right < len) {
            char r = s.charAt(right);
            if (map.containsKey(r)) {
                // 如果r上次出现的位置在窗口外，则不变
                // 如果r上次出现的位置在窗口内，left更新至r上次出现位置的下一位
                left = Math.max(left, map.get(r) + 1);
            }
            map.put(r, right);
            // 比较记录的最大值和当前窗口长度
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}