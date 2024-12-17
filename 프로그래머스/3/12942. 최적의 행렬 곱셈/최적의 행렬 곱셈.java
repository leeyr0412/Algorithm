class Solution {
    public int solution(int[][] matrix_sizes) {
        int[][] dp = new int[matrix_sizes.length][matrix_sizes.length];
        for (int i = 0; i < matrix_sizes.length; i++) {
            dp[i][i] = 0;
        }
        for (int size = 1; size <= matrix_sizes.length; size++) {
            for (int start = 0; start < matrix_sizes.length; start++) {
                int end = start + size;
                if (end >= matrix_sizes.length) {
                    continue;
                }
                dp[start][end] = (int) 10e10;
                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + (matrix_sizes[start][0] * matrix_sizes[mid][1] * matrix_sizes[end][1]));
                }
            }
        }
        return dp[0][matrix_sizes.length - 1];
    }
}