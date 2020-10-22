class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<int[]> freq = new ArrayList<>();
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();

        if (candidates.length == 0) {
            return answer;
        }

        Arrays.sort(candidates);

        // 构造一个 <数据:频率> 的“字典”，对这个字典进行迭代而不是原列表
        // 以此达到规避重复元素的目的
        for (int cand : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || freq.get(size - 1)[0] != cand) {
                freq.add(new int[]{cand, 1});
            } else {
                freq.get(size - 1)[1]++;
            }
        }

        // 深度优先搜索
        dfs(freq, target, answer, combine, 0);

        return answer;
    }

    public void dfs(List<int[]> freq, int target, List<List<Integer>> answer, List<Integer> combine, int pos) {
        // 搜索成功，添加结果
        if (target == 0) {
            answer.add(new ArrayList<Integer>(combine));
            return;
        }
        
        // 终止条件，注意一定要放在搜索成功条件之后，否则搜索结果无法被收集
        if (pos == freq.size() || freq.get(pos)[0] > target) {
            return;
        }

        // 跳过当前这个数
        dfs(freq, target, answer, combine, pos + 1);

        // 找到当前这个数最多可以使用几次
        int most = Math.min(target / freq.get(pos)[0], freq.get(pos)[1]);
        // 遍历使用1至most次的情况
        for (int i = 1; i <= most; i++) {
            combine.add(freq.get(pos)[0]);
            dfs(freq, target - i * freq.get(pos)[0], answer, combine, pos + 1);
        }

        // 做回溯
        for (int i = 1; i <= most; i++) {
            combine.remove(combine.size() - 1);
        }
        return;
    }
}