class Solution {
    public void nextPermutation(int[] nums) {
        // 需要找出下一个排列的规律
        // 从左到右找到最后一个相邻升序对(left, right)
        // 最后一个相邻升序对的右侧为单调递减，最小值与right进行比较，若大于right，则更新right值
        // e.g. 1,3,2 -> 2,1,3     2,3,1 -> 3,1,2
        // 确定left和right之后，交换对应位置的值，然后对left右侧（单调递减）进行逆序操作（单调递增）
        
        int left = -1;
        int right = -1;

        int n = nums.length;

		// 尽管有两个循环，实际时间复杂度为O(n)，空间复杂度O(1)
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i+1]) {
                left = i;
                right = i + 1;

                for (int j = i + 1; j < n - 1; j++) {
                    if (nums[j] < nums[j+1]) {
                        i = j - 1;
                        break;
                    } else if (nums[j] >= nums[j+1] && nums[left] < nums[j+1]){
						right = j + 1;
                        continue;
                    }
                }
            }
        }

        if (left == -1 && right == -1) {
            arrayReverse(nums, 0);
            return;
        }

        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
		
		// 一开始自己写的时候用的是Arrays.sort()，时间复杂度不如arrayReverse()操作
        arrayReverse(nums, left + 1);
        return;
    }

    public void arrayReverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}