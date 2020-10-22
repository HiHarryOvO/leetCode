class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        // 边界情况
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return answer;
        }

        // 确定上下左右四个边界
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;

        // 
        while (left <= right && top <= bottom) {
            for (int col = left; col <= right; col++) {
                answer.add(matrix[top][col]);
            }

            for (int row = top + 1; row <= bottom; row++) {
                answer.add(matrix[row][right]);
            }

            if (left < right && top < bottom) {
                for (int col = right - 1; col >= left; col--) {
                    answer.add(matrix[bottom][col]);
                }

                for (int row = bottom - 1; row > left; row--) {
                    answer.add(matrix[row][left]);
                }
            }

            left++;
            right--;
            top++;
            bottom--;

        }

        return answer;
    }
}
