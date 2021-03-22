class Solution {
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] ch = s.toCharArray();
        // 哈希表存储字符最后出现的位置（重复字符则置-1）
        for (int i = 0; i < ch.length; i++) {
            if (!map.containsKey(ch[i])) {
                map.put(ch[i], i);
            } else {
                map.put(ch[i], -1);
            }
        }

        char minChar = ' ';
        int minIndex = Integer.MAX_VALUE;

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            char c = (char)entry.getKey();
            int index = (int)entry.getValue();

            if (index != -1 && index < minIndex) {
                minIndex = index;
                minChar = c;
            }
        }

        return minChar;
    }
}