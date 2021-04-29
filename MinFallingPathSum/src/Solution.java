// DP
// Use a dp matrix, dp[i][j] represents the minimum falling path sum from i, j to the last row
// Initialize dp[n-1][j] to the value of the last row of matrix, for the rest to Integer.MIN_VALUE
// Base Case:
// If j is not valid, return
// If i reaches the last row, return dp[i][j]
// If dp[i][j] is not Integer.MIN_VALUE, return dp[i][j]
// At each position i, j, try all possible j in row i + 1 and get the min value
// Set dp[i][j] = min + matrix[i][j] (Cost at current position), return it
// Time: O(n^2), Space: O(n^2)

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int [][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == n-1) {
                    dp[i][j] = matrix[i][j];
                }
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int start = 0; start < n; start++) {
            res = Math.min(res, helper(0, start, matrix, dp));
        }
        return res;
    }

    private int helper(int i, int j, int [][] matrix, int [][] dp) {
        if(j < 0 || j >= matrix.length) {
            return Integer.MAX_VALUE;
        }
        else if(i == matrix.length - 1 || dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }

        for(int k = j-1; k <= j+1; k++) {
            dp[i][j] = Math.min(dp[i][j], helper(i+1, k, matrix, dp));
        }
        dp[i][j] += matrix[i][j];
        return dp[i][j];
    }
}
