class Solution {
    // 方法1
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> ans = new HashMap<>();

        for (String str : strs) {
            // 对字符串进行排序来解决问题
            char[] chr = str.toCharArray();
            Arrays.sort(chr);
            String key = String.valueOf(chr);
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList());
            ans.get(key).add(str);
        }

        return new ArrayList(ans.values());
    }

    // 方法2
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> ans = new HashMap<>();
        // 构建散列函数来解决问题
        int[] count = new int[26];
        
        for (String str : strs) {
            Arrays.fill(count, 0);

            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder stb = new StringBuilder();
            for (int num : count) {
                stb.append('#');
                stb.append(num);
            }

            String key = stb.toString();

            if (!ans.containsKey(key))
                ans.put(key, new ArrayList());
            ans.get(key).add(str);
        }

        return new ArrayList(ans.values());
    }
}
