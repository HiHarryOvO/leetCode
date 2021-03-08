class Solution {
    public int minArray(int[] numbers) {
        int n = numbers.length;
        int target = binSearch(numbers, 0, n - 1);
        return numbers[target];
    }

    public int binSearch(int[] numbers, int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] > numbers[high]) {
                low = mid + 1; // 最小数字肯定不在mid这一侧，因此+1
            } else if (numbers[mid] < numbers[high]) {
                high = mid;
            } else {
                high -= 1; // 由于mid和high相同，排除high一定不会出错
            }
        }
        return low;
    }
}