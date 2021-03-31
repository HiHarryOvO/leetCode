class Solution {
    List<List<Integer>> finalSol = new LinkedList<>();
    List<Integer> sol = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序 nlogn
        Arrays.sort(nums);
        dfs(false, nums, 0);
        return finalSol;
    }

    private void dfs(boolean choosePre, int[] nums, int cur) {
        if (cur == nums.length) {
            finalSol.add(new LinkedList<Integer>(sol));
            return;
        }

        dfs(false, nums, cur + 1);

        // 如果未选择前面的相等元素生成子集，则跳过当前元素
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }

        sol.add(nums[cur]);
        dfs(true, nums, cur + 1);
        sol.remove(sol.size() - 1);
    }
}