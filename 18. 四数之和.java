class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 双指针，更具体地说：二重循环+双指针
        // 二重循环中加入极端位置的判断，可减少循环次数

        List<List<Integer>> answer = new ArrayList<>();
        
        // 需要被考虑进去的情况
        if (nums == null || nums.length < 4) {
            return answer;
        }
        
        int n = nums.length;
        Arrays.sort(nums);

        for (int first = 0; first < n - 3; first++) {
            if (first > 0 && nums[first] == nums[first-1]) {
                continue;
            }
            
            // 最小的情况
            if (nums[first] + nums[first+1] + nums[first+2] + nums[first+3] > target) {
                break;
            }

            // 最大的情况
            if (nums[first] + nums[n-3] + nums[n-2] + nums[n-1] < target) {
                continue;
            }

            for (int second = first + 1; second < n - 2; second++) {
                if (second > first + 1 && nums[second] == nums[second-1]) {
                    continue;
                }

                if (nums[first] + nums[second] + nums[second+1] + nums[second+2] > target) {
                    break;
                }

                if (nums[first] + nums[second] + nums[n-2] + nums[n-1] < target) {
                    continue;
                }

                int fourth = n - 1;
                int third = second + 1;
                
                while (third < fourth) {
                    int sum = nums[first] + nums[second] + nums[third] + nums[fourth];
                    if (sum == target) {
                        answer.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));
                        while (third < fourth && nums[third] == nums[third+1]) {
                            third++;
                        }
                        third++;

                        while (third < fourth && nums[fourth] == nums[fourth-1]) {
                            fourth--;
                        }
                        fourth--;
                    } else if (sum < target) {
                        third++;
                    } else {
                        fourth--;
                    }
                } 
            }
        }
        return answer;
    }
}