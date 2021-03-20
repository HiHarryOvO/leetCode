// 使用堆的写法
class Solution {
    public String minNumber(int[] nums) {
        // 需要小顶堆，我的理解是，s1大于s2返回正数就是默认，小顶堆，s1大于s2返回负数就是逆序，大顶堆
        PriorityQueue<String> queue = new PriorityQueue<>((s1, s2) -> (s1 + s2).compareTo(s2 + s1));

        // 入堆
        for (int num : nums) {
            queue.offer(String.valueOf(num));
        }

        // 出堆
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }
}

// 快速排序
class Solution {
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        // 快排
        quickSort(strs, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    void quickSort(String[] strs, int l, int r) {
        if (l >= r) {
            return;
        }
        String p = strs[l];
        int i = l, j = r;
        while (i < j) {
            // p < strs[j]
            while (i < j && (p + strs[j]).compareTo(strs[j] + p) <= 0) j--;
            strs[i] = strs[j];
            // p > strs[i]
            while (i < j && (p + strs[i]).compareTo(strs[i] + p) >= 0) i++;
            strs[j] = strs[i];
        }
        strs[i] = p;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }
}

// 内置函数
class Solution {
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        // 排序
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));

        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }
}