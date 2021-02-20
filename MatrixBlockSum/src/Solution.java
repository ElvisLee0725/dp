// Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.

// Create a DP with m rows, n columns. While each item at column 0 is 0. dp[i][j] is the sum from mat[i][j-1]
// Iterate the mat, at index i, j, get the row range of Math.max(0, i+K) to Math.min(m-1, i+K) and sum up their dp[k][Math.min(n-1, j+K) + 1] - dp[k][Math.max(0, j-K)] and store in res[i][j]
// Time: O(nmK), Space: O(nm)
class Solution {
    public static void main(String[] args) {
        int [][] mat = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int [][] ans = new Solution().matrixBlockSum(mat, 1);
        for(int [] arr : ans) {
            for(int n : arr) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int [][] dp = new int[m][n+1];
        for(int i = 0; i < m; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(j == 1) {
                    dp[i][j] = mat[i][j-1];
                }
                else {
                    dp[i][j] = dp[i][j-1] + mat[i][j-1];
                }
            }
        }
        int [][] res = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int sum = 0;
                for(int k = Math.max(0, i-K); k <= Math.min(m-1, i+K); k++) {
                    sum += dp[k][Math.min(n-1, j+K) + 1] - dp[k][Math.max(0, j-K)];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }
}
