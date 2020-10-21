class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();

        // 深度优先搜索
        dfs(candidates, target, answer, combine, 0);

        return answer;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> answer, List<Integer> combine, int idx) {
        // 因为要使用递归，需要思考递归终止的条件
        if (idx == candidates.length) {
            return;
        }

        if (target == 0) {
            // 这个地方不使用new生成新对象的话，相当于answer中所有结果共享同一个实例
            // 那么在回溯结束后所以结果都会被清空
            answer.add(new ArrayList<Integer>(combine)); 
            return;
        }

        // 跳过idx所在的元素
        dfs(candidates, target, answer, combine, idx + 1);

        // 判断条件能够限制树的增长，起到剪枝作用
        if (target - candidates[idx] >= 0) {
            // 添加当前可行元素
            combine.add(candidates[idx]);
            
            // 递归部分
            dfs(candidates, target - candidates[idx], answer, combine, idx);
            
            // 回溯
            combine.remove(combine.size() - 1);
        }

        return;
    }
}
