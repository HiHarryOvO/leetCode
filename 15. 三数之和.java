class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 使用双指针，固定第一个数，第二三个数分别从前后向中间更新
        // 首先排序
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // 遇到相同的数需要跳过
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            
            int target = - nums[i];
            int k = nums.length - 1;

            for(int j = i + 1; j < nums.length; j++) {
                // 同理，跳过相同的数
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }

                // 更新尾指针
                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }

                // 两个指针汇合时，所有可能性结束，取下一个i
                if (j == k) {
                    break;
                }

                if (nums[j] + nums[k] == target) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(nums[i]);
                    pair.add(nums[j]);
                    pair.add(nums[k]);
                    answer.add(pair);
                }   
            }
        }
        return answer;
    }
}
