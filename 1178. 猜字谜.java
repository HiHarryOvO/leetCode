class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                mask |= 1 << (ch - 'a');
            }
            // 由于puzzle长度等于7，因此word的二进制长度（即包含的不同字母数）不能大于7
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int total = 0, mask = 0;
            for (int i = 1; i < 7; i++) {
                mask |= 1 << (puzzle.charAt(i) - 'a');
            }
            int subset = mask;
            do {
                // 这里是保证s的第一位一定为1
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                // 循环遍历余下六个字母的所有子集
                subset = (subset - 1) & mask;
                // subset是不含最高位的 即使是subset = 0 还需要执行一次循环(即子集只含首字母的情况) 1000000
                // -1的二进制表达为1111 1111，此时满足循环终止条件
            } while (subset != mask);

            ans.add(total);
        }
        return ans;
    }
}