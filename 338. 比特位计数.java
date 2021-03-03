// 自己写的，时间复杂度和空间复杂度是最优
// 但实现有点绕，有点冗杂
class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        // 我想的是以2的幂作为一个起点，如果比i小的最接近i的2的幂记为curr的话，动态规划公式如下：
        // res[i] = 1 + res[i - curr];
        int curr = 1, next = 2;
        for (int i = 1; i <= num; i++) {
            if (i == curr) {
                res[i] = 1;
            } else if (i == next) {
                res[i] = 1;
                curr = next;
                next *= 2;
            } else {
                res[i] = 1 + res[i - curr];
            }
        }
        return res;
    }
}

// 和我的解法思路相同，但实现更简洁
class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++) {
            // 判断一个数是不是2的幂的简单方法
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = 1 + bits[i - highBit];
        }
        return bits;
    }
}

class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        // 如果是奇数，bits[i] = bits[i/2] + 1
        // 如果是偶数，bits[i] = bits[i/2]
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
}

// 最低设置位思想
class Solution {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}