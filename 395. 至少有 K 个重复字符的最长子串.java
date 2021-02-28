// 解法1：分治
class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        // 分治算法
        return dfs(s, 0, n - 1, k);
    }

    public int dfs(String s, int l, int r, int k) {
        // 统计当前子串每个字母的频率
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            char ch = s.charAt(i);
            cnt[ch - 'a']++;
        }

        // 找出不满足条件的字母
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }

        // 如果当前子串字母频率都大于k，返回当前子串长度
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        // 按照split继续分治当前子串的子串
        while (i <= r) {
            // 寻找第一个不等于split的字符作为子串起点
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            // 如果找不到不等于split的字符，返回ret
            if (i > r) {
                break;
            }

            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            // i - 1 为我们寻找的子串的终点（包含）
            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }
}

// 解法2：滑动窗口
class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        int ret = 0;
        // 枚举最长子串中的字符种类数量
        for (int t = 1; t <= 26; t++) {
            // tot表示当前子串中字符种类数量
            // less表示当前子串中频率在(0,k)之间的字符种类数量
            int tot = 0, less = 0;
            int l = 0, r = 0;
            int[] cnt = new int[26];
            while (r < n) {
                cnt[s.charAt(r) - 'a']++;
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    tot++;
                    less++;
                }
                if (cnt[s.charAt(r) - 'a'] == k) {
                    less--;
                }

                while (tot > t) {
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        tot--;
                        less--;
                    }
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    l++;
                }
                // 当前子串没有不符合条件的字符时可以与ret进行比较
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }
}