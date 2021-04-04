class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int ans : answers) {
            // 如果第一次出现这一种类，或者这一种类的名额用完了，则对最终结果加上 ans + 1
            if (!map.containsKey(ans) || map.get(ans) == 0) {
                map.put(ans, ans);
                res += ans + 1;
            } else {
                map.put(ans, map.get(ans) - 1);
            }
        }
        return res;
    }
}