class Solution {
    int[] nums, tmp;
    
    public int reversePairs(int[] nums) {
        this.nums = nums;
        this.tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }

    // 归并排序
    private int mergeSort(int l, int r) {
        // 子数组中元素个数小于等于1时，0个逆序对
        if (l >= r) {
            return 0;
        }

        int mid = l + (r - l) / 2;
        // 递归划分，结果需要把之前的逆序对算进来
        int res = mergeSort(l, mid) + mergeSort(mid + 1, r);

        for (int k = l; k <= r; k++) {
            tmp[k] = nums[k];
        }

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            // 左侧数组已经用完
            if (i == mid + 1) {
                nums[k] = tmp[j++];
            } else if (j == r + 1 || tmp[i] <= tmp[j]) { // 右侧数组已经用完或者tmp[i]小于等于tmp[j]
                nums[k] = tmp[i++];
            } else { // 插入右侧数组的元素，此时引入的逆序对数量为 mid - i + 1
                nums[k] = tmp[j++];
                res += mid - i + 1;
            }
        }

        return res;
    }
}